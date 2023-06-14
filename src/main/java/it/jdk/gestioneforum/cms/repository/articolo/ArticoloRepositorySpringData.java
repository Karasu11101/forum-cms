package it.jdk.gestioneforum.cms.repository.articolo;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticoloRepositorySpringData extends CrudRepository<ArticoloEntity, Integer> {
//    @Query("select art from ArticleEntity art where art.titolo = :titolo")
    public Optional<ArticoloEntity> findByTitolo(@Param("titolo") String titolo) throws RepositoryException;
}
