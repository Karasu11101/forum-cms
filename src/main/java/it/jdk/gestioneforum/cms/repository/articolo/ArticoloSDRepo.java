package it.jdk.gestioneforum.cms.repository.articolo;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;
import it.jdk.gestioneforum.cms.model.Sezione;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import it.jdk.gestioneforum.cms.persistence.CategoriaEntity;
import it.jdk.gestioneforum.cms.persistence.SezioneEntity;
import it.jdk.gestioneforum.cms.repository.categoria.CategoriaRepositorySpringData;
import it.jdk.gestioneforum.cms.repository.sezione.SezioneRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class ArticoloSDRepo implements RepositoryArticolo {

    private final ArticoloRepositorySpringData articoloSDRepo;
    private final CategoriaRepositorySpringData categoriaSDRepo;
    private final SezioneRepositorySpringData sezioneSDRepo;

    @Autowired
    public ArticoloSDRepo(ArticoloRepositorySpringData articoloSDRepo,
                          CategoriaRepositorySpringData categoriaSDRepo,
                          SezioneRepositorySpringData sezioneSDRepo) {
        this.articoloSDRepo = articoloSDRepo;
        this.categoriaSDRepo = categoriaSDRepo;
        this.sezioneSDRepo = sezioneSDRepo;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class}) // readOnly e rollback hanno i valori di default
    public Articolo createArticolo(Articolo articolo) throws RepositoryException {

        Optional<SezioneEntity> sezioneEntity = sezioneSDRepo.findById(articolo.getSezione().getId());

        Optional<CategoriaEntity> categoriaEntity = categoriaSDRepo.findById(articolo.getCategoria().getId());

        if(sezioneEntity.isPresent() && categoriaEntity.isPresent()) {
            ArticoloEntity articoloEntity = new ArticoloEntity();
            articoloEntity.setTitolo(articolo.getTitolo());
            articoloEntity.setTesto(articolo.getTesto());
            articoloEntity.setData(articolo.getData());
            articoloEntity.setSezione(sezioneEntity.get());
            articoloEntity.setCategoria(categoriaEntity.get());

            articoloSDRepo.save(articoloEntity);

            articolo.setId(articoloEntity.getId());
            articolo.setVersione(articoloEntity.getVersione() + 1);

            return articolo;
        } else
            throw new RepositoryException("La sezione o la categoria specificati non sono stati trovati");
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public Articolo updateArticolo(Articolo articolo) throws RepositoryException {
        Optional<ArticoloEntity> articoloEntity = articoloSDRepo.findById(articolo.getId());
        Optional<SezioneEntity> sezioneEntity = sezioneSDRepo.findById(articolo.getSezione().getId());
        Optional<CategoriaEntity> categoriaEntity = categoriaSDRepo.findById(articolo.getCategoria().getId());

        if(articoloEntity.isPresent() && sezioneEntity.isPresent() && categoriaEntity.isPresent()) {

            articoloEntity.get().setTitolo(articolo.getTitolo());
            articoloEntity.get().setTesto(articolo.getTesto());
            articoloEntity.get().setData(articolo.getData());
            articoloEntity.get().setSezione(sezioneEntity.get());
            articoloEntity.get().setCategoria(categoriaEntity.get());

            articolo.setVersione(articoloEntity.get().getVersione() + 1);

            return articolo;
        } else
            throw new RepositoryException("L'articolo con id " + articolo.getId() + " non è stato trovato");
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, RepositoryException.class})
    public void deleteArticolo(Articolo articolo) throws RepositoryException {
        Optional<ArticoloEntity> articoloEntity = articoloSDRepo.findById(articolo.getId());

        if(articoloEntity.isPresent()) {
            articoloSDRepo.delete(articoloEntity.get());
        } else
            throw new RepositoryException("L'articolo con id " + articolo.getId() + " non è stato trovato");
    }

    @Override
    public Articolo showArticolo(String titolo) throws RepositoryException {
        String newTitolo = titolo.replaceAll("%20", " ");
        Optional<ArticoloEntity> articoloEntity = articoloSDRepo.findByTitolo(newTitolo);
        if(articoloEntity.isPresent()) {
            return convertArticolo(articoloEntity.get());
        } else
            throw new RepositoryException("L'articolo richiesto non è stato trovato");
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

}
