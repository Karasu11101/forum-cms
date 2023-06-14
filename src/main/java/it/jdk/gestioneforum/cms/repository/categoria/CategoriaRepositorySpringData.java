package it.jdk.gestioneforum.cms.repository.categoria;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.persistence.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CategoriaRepositorySpringData extends JpaRepository<CategoriaEntity, Integer> {

    @Query("select c from CategoriaEntity c where c.titolo = :titolo")
    public Optional<CategoriaEntity> findByTitolo(@Param("titolo") String titolo) throws RepositoryException;
}
