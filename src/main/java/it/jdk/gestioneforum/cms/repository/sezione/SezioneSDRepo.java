package it.jdk.gestioneforum.cms.repository.sezione;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;
import it.jdk.gestioneforum.cms.model.Sezione;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import it.jdk.gestioneforum.cms.persistence.SezioneEntity;
import it.jdk.gestioneforum.cms.repository.categoria.CategoriaRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SezioneSDRepo(SezioneRepositorySpringData sezioneSDRepo, CategoriaRepositorySpringData categoriaSDRepo) {
        this.sezioneSDRepo = sezioneSDRepo;
        this.categoriaSDRepo = categoriaSDRepo;
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

        if (sezioneEntity.isPresent()) {
            sezioneSDRepo.delete(sezioneEntity.get());
        } else
            throw new RepositoryException("La sezione con id " + sezione.getId() + " non è stata trovata");
    }

    @Override
    public List<Articolo> showArticoli(String titoloSezione) throws RepositoryException {
        Optional<SezioneEntity> sezioneEntityOp = sezioneSDRepo.findByTitolo(titoloSezione);
        if (sezioneEntityOp.isPresent()) {
            List<ArticoloEntity> articoliEntity = sezioneSDRepo.findAllArticlesById(sezioneEntityOp.get().getId());
            List<Articolo> articoli = new ArrayList<>();
            for (ArticoloEntity ent : articoliEntity) {
                Articolo articolo = convertArticolo(ent);
                articoli.add(articolo);
            }
            return articoli;
        } else
            throw new RepositoryException("Non è stato trovato nessun articolo relativo a questa sezione");
    }

    @Override
    public Sezione showSezione(String titolo) throws RepositoryException {
        Optional<SezioneEntity> sezioneEntity = sezioneSDRepo.findByTitolo(titolo);
        if (sezioneEntity.isPresent()) {
            return convertSezione(sezioneEntity.get());
        } else
            throw new RepositoryException("La categoria richiesta non è stata trovata");
    }

    private Articolo convertArticolo(ArticoloEntity articoloEntity) {
        Articolo articolo = new Articolo();

        Sezione sezione = new Sezione();
        sezione.setTitolo(articoloEntity.getSezione().getTitolo());
        sezione.setDescrizione(articoloEntity.getSezione().getDescrizione());
        sezione.setId(articoloEntity.getSezione().getId());
        sezione.setVersione(articoloEntity.getSezione().getVersione());

        Categoria categoria = new Categoria();
        categoria.setTitolo(articoloEntity.getSezione().getTitolo());
        categoria.setDescrizione(articoloEntity.getSezione().getDescrizione());
        categoria.setId(articoloEntity.getSezione().getId());
        categoria.setVersione(articoloEntity.getSezione().getVersione());

        articolo.setId(articoloEntity.getId());
        articolo.setTitolo(articolo.getTitolo());
        articolo.setTesto(articolo.getTesto());
        articolo.setSezione(sezione);
        articolo.setCategoria(categoria);
        articolo.setVersione(articoloEntity.getVersione());

        return articolo;
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