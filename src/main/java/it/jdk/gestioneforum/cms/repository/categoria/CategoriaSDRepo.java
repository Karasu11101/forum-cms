package it.jdk.gestioneforum.cms.repository.categoria;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;
import it.jdk.gestioneforum.cms.model.Sezione;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import it.jdk.gestioneforum.cms.persistence.CategoriaEntity;
import it.jdk.gestioneforum.cms.repository.sezione.SezioneRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class CategoriaSDRepo implements RepositoryCategoria {

    private final CategoriaRepositorySpringData categoriaSDRepo;
    private final SezioneRepositorySpringData sezioneSDRepo;

    @Autowired
    public CategoriaSDRepo(CategoriaRepositorySpringData categoriaSDRepo, SezioneRepositorySpringData sezioneSDRepo) {
        this.categoriaSDRepo = categoriaSDRepo;
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
        Optional<CategoriaEntity> categoriaEntityOp = categoriaSDRepo.findById(categoria.getId());

        if(categoriaEntityOp.isPresent()) {
            categoriaSDRepo.delete(categoriaEntityOp.get());
        } else
            throw new RepositoryException("La categoria con id " + categoria.getId() + " non è stata trovata");
    }

    @Override
    public List<Articolo> showArticoli(String titolo) throws RepositoryException {
        Optional<CategoriaEntity> categoriaEntityOp = categoriaSDRepo.findByTitolo(titolo);
        if(categoriaEntityOp.isPresent()) {
            List<ArticoloEntity> articoliEntity = categoriaSDRepo.findAllArticlesById(categoriaEntityOp.get().getId());
            List<Articolo> articoli = new ArrayList<>();
            for(ArticoloEntity ent : articoliEntity) {
                Articolo articolo = convertArticolo(ent);
                articoli.add(articolo);
            }
            return articoli;
        } else
            throw new RepositoryException("Non è stato trovato nessun articolo relativo a questa categoria");
    }

    @Override
    public Categoria showCategoria(String titolo) throws RepositoryException {
        Optional<CategoriaEntity> categoriaEntity = categoriaSDRepo.findByTitolo(titolo);
        if(categoriaEntity.isPresent()) {
            return convertCategoria(categoriaEntity.get());
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

    private Categoria convertCategoria(CategoriaEntity categoriaEntity) {
        Categoria categoria = new Categoria();

        categoria.setId(categoriaEntity.getId());
        categoria.setTitolo(categoriaEntity.getTitolo());
        categoria.setDescrizione(categoriaEntity.getDescrizione());
        categoria.setVersione(categoriaEntity.getVersione());

        return categoria;
    }
}
