package it.jdk.gestioneforum.cms.repository.categoria;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import it.jdk.gestioneforum.cms.persistence.CategoriaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CategoriaRepositorySpringData extends CrudRepository<CategoriaEntity, Integer> {
    @Query("select a from ArticoloEntity a join a.categoria c where :id_categoria = c.id")
    List<ArticoloEntity> findAllArticlesById(@Param ("id_categoria") Integer id_categoria);

//    @Query("select cat from CategoriaEntity cat where cat.titolo = :titolo")
    public Optional<CategoriaEntity> findByTitolo(@Param("titolo") String titolo) throws RepositoryException;
}
