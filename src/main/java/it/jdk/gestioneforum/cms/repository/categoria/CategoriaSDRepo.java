package it.jdk.gestioneforum.cms.repository.categoria;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import it.jdk.gestioneforum.cms.persistence.CategoriaEntity;
import it.jdk.gestioneforum.cms.repository.articolo.ArticoloRepositorySpringData;
import it.jdk.gestioneforum.cms.repository.sezione.SezioneRepositorySpringData;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class CategoriaSDRepo implements RepositoryCategoria {

    private final CategoriaRepositorySpringData categoriaSDRepo;
    private final ArticoloRepositorySpringData articoloSDRepo;
    private final SezioneRepositorySpringData sezioneSDRepo;

    public CategoriaSDRepo(CategoriaRepositorySpringData categoriaSDRepo,
                           ArticoloRepositorySpringData articoloSDRepo,
                           SezioneRepositorySpringData sezioneSDRepo) {
        this.categoriaSDRepo = categoriaSDRepo;
        this.articoloSDRepo = articoloSDRepo;
        this.sezioneSDRepo = sezioneSDRepo;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public Categoria createCategoria(Categoria categoria) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setTitolo(categoria.getTitolo());
        categoriaEntity.setDescrizione(categoria.getDescrizione());

        categoriaSDRepo.save(categoriaEntity);

        categoria.setId(categoriaEntity.getId());
        categoria.setVersione(categoriaEntity.getVersione() + 1);

        return categoria;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public Categoria updateCategoria(Categoria categoria) throws RepositoryException {
        Optional<CategoriaEntity> categoriaEntityOp = categoriaSDRepo.findById(categoria.getId());

        if(categoriaEntityOp.isPresent()) {
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            categoriaEntity.setTitolo(categoria.getTitolo());
            categoriaEntity.setDescrizione(categoria.getDescrizione());

//            List<ArticoloEntity> articoloEntities = new ArrayList<>();
//
//            for(Articolo a : categoria.getArticoli()) {
//                Optional<SezioneEntity> sezioneEntityOp = sezioneSDRepo.findById(a.getSezione().getId());
//                ArticoloEntity articoloEntity = new ArticoloEntity();
//                articoloEntity.setId(a.getId());
//                articoloEntity.setTitolo(a.getTitolo());
//                articoloEntity.setTesto(a.getTesto());
//                articoloEntity.setData(a.getData());
//                articoloEntity.setSezione(sezioneEntityOp.get());
//                articoloEntity.setCategoria(categoriaEntity);
//                articoloEntity.setVersione(a.getVersione());
//
//                articoloEntities.add(articoloEntity);
//            }

            categoria.setVersione(categoriaEntity.getVersione() + 1);

            return categoria;
        } else
            throw new RepositoryException("La categoria con id " + categoria.getId() + " non è stata trovata");
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public void deleteCategoria(Categoria categoria) throws RepositoryException {
        Optional<CategoriaEntity> categoriaEntity = categoriaSDRepo.findById(categoria.getId());
        List<ArticoloEntity> articoli = articoloSDRepo.findAllArticlesByIdCategoria(categoria.getId());
        if (categoriaEntity.isPresent() && articoli.isEmpty()) {
            categoriaSDRepo.delete(categoriaEntity.get());
        } else
            throw new RepositoryException("La categoria con id " + categoria.getId() +
                    " non è stata trovata, oppure sono ancora presenti articoli in questa sezione");
    }

    @Override
    public List<Articolo> showArticoli(Integer id) throws RepositoryException {
        Optional<CategoriaEntity> categoriaEntityOp = categoriaSDRepo.findById(id);
        if(categoriaEntityOp.isPresent()) {
            Pageable pageRequest = PageRequest.of(0, 5);
            List<ArticoloEntity> articoliEntity =
                    articoloSDRepo.findAllByCategoria(categoriaEntityOp.get().getId(), pageRequest);
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
    public Categoria showCategoria(Integer id) throws RepositoryException {
        Optional<CategoriaEntity> categoriaEntity = categoriaSDRepo.findById(id);
        if(categoriaEntity.isPresent()) {
            return convertCategoria(categoriaEntity.get());
        } else
            throw new RepositoryException("La categoria richiesta non è stata trovata");
    }

    private Categoria convertCategoria(CategoriaEntity categoriaEntity) {
        Categoria categoria = new Categoria();

        categoria.setId(categoriaEntity.getId());
        categoria.setTitolo(categoriaEntity.getTitolo());
        categoria.setDescrizione(categoriaEntity.getDescrizione());
        categoria.setVersione(categoriaEntity.getVersione());

        return categoria;
    }
}
