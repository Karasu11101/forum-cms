package it.jdk.gestioneforum.cms.repository.sezione;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import it.jdk.gestioneforum.cms.persistence.SezioneEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SezioneRepositorySpringData extends CrudRepository<SezioneEntity, Integer> {

    @Query("select a from ArticoloEntity a join a.sezione s where :id_sezione = s.id")
    List<ArticoloEntity> findAllArticlesById(@Param("id_sezione") Integer id_sezione);

//    @Query("select s from SezioneEntity s where s.titolo = :titolo")
    public Optional<SezioneEntity> findByTitolo(@Param("titolo") String titolo) throws RepositoryException;
}
