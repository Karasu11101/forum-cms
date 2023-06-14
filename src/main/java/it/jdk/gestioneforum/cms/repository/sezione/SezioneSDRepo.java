package it.jdk.gestioneforum.cms.repository.sezione;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Sezione;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import it.jdk.gestioneforum.cms.persistence.SezioneEntity;
import it.jdk.gestioneforum.cms.repository.articolo.ArticoloRepositorySpringData;
import it.jdk.gestioneforum.cms.repository.categoria.CategoriaRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class SezioneSDRepo implements RepositorySezione {

    private final SezioneRepositorySpringData sezioneSDRepo;
    private final CategoriaRepositorySpringData categoriaSDRepo;
    private final ArticoloRepositorySpringData articoloSDRepo;

    @Autowired
    public SezioneSDRepo(SezioneRepositorySpringData sezioneSDRepo,
                         CategoriaRepositorySpringData categoriaSDRepo,
                         ArticoloRepositorySpringData articoloSDRepo) {
        this.sezioneSDRepo = sezioneSDRepo;
        this.categoriaSDRepo = categoriaSDRepo;
        this.articoloSDRepo = articoloSDRepo;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public Sezione createSezione(Sezione sezione) {
        SezioneEntity sezioneEntity = new SezioneEntity();
        sezioneEntity.setTitolo(sezione.getTitolo());
        sezioneEntity.setDescrizione(sezione.getDescrizione());

        sezioneSDRepo.save(sezioneEntity);

        sezione.setId(sezioneEntity.getId());
        sezione.setVersione(sezioneEntity.getVersione() + 1);

        return sezione;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public Sezione updateSezione(Sezione sezione) throws RepositoryException {
        Optional<SezioneEntity> sezioneEntityOp = sezioneSDRepo.findById(sezione.getId());

        if (sezioneEntityOp.isPresent()) {
            SezioneEntity sezioneEntity = sezioneEntityOp.get();
            sezioneEntity.setTitolo(sezione.getTitolo());
            sezioneEntity.setDescrizione(sezione.getDescrizione());

//            List<ArticoloEntity> articoloEntities = new ArrayList<>();

//            for (Articolo a : sezione.getArticoli()) {
//                Optional<CategoriaEntity> categoriaEntityOp = categoriaSDRepo.findById(a.getCategoria().getId());
//
//                if(categoriaEntityOp.isPresent()) {
//                    ArticoloEntity articoloEntity = new ArticoloEntity();
//                    articoloEntity.setId(a.getId());
//                    articoloEntity.setTitolo(a.getTitolo());
//                    articoloEntity.setTesto(a.getTesto());
//                    articoloEntity.setData(a.getData());
//                    articoloEntity.setSezione(sezioneEntity);
//                    articoloEntity.setCategoria(categoriaEntityOp.get());
//                    articoloEntity.setVersione(a.getVersione());
//
//                    articoloEntities.add(articoloEntity);
//                }
//            }

            sezione.setVersione(sezioneEntity.getVersione() + 1);

            return sezione;
        } else
            throw new RepositoryException("La sezione con id " + sezione.getId() + " non è stata trovata");
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public void deleteSezione(Sezione sezione) throws RepositoryException {
        Optional<SezioneEntity> sezioneEntity = sezioneSDRepo.findById(sezione.getId());
        List<ArticoloEntity> articoli = articoloSDRepo.findAllArticlesByIdSezione(sezione.getId());
        if (sezioneEntity.isPresent() && articoli.isEmpty()) {
            sezioneSDRepo.delete(sezioneEntity.get());
        } else
            throw new RepositoryException("La sezione con id " + sezione.getId() +
                    " non è stata trovata, oppure sono ancora presenti articoli in questa sezione");
    }

    @Override
    public List<Articolo> showArticoli(Integer id) throws RepositoryException {
        Optional<SezioneEntity> sezioneEntityOp = sezioneSDRepo.findById(id);
        if(sezioneEntityOp.isPresent()) {
            Pageable pageRequest = PageRequest.of(0, 5);
            List<ArticoloEntity> articoliEntity =
                    articoloSDRepo.findAllBySezione(sezioneEntityOp.get().getId(), pageRequest);
            List<Articolo> articoli = new ArrayList<>();
            for(ArticoloEntity a : articoliEntity) {
                Articolo articolo = new Articolo(a.getTitolo(), a.getData(), a.getTesto());
                articoli.add(articolo);
            }
            return articoli;
        } else
            throw new RepositoryException("Non è stato trovato nessun articolo relativo a questa categoria");
    }

    @Override
    public Sezione showSezione(Integer id) throws RepositoryException {
        Optional<SezioneEntity> sezioneEntity = sezioneSDRepo.findById(id);
        if (sezioneEntity.isPresent()) {
            return convertSezione(sezioneEntity.get());
        } else
            throw new RepositoryException("La categoria richiesta non è stata trovata");
    }

    private Sezione convertSezione(SezioneEntity sezioneEntity) {
        Sezione sezione = new Sezione();

        sezione.setId(sezioneEntity.getId());
        sezione.setTitolo(sezioneEntity.getTitolo());
        sezione.setDescrizione(sezioneEntity.getDescrizione());
        sezione.setVersione(sezioneEntity.getVersione());

        return sezione;
    }
}