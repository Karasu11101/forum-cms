package it.jdk.gestioneforum.cms.repository.articolo;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.persistence.ArticoloEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticoloRepositorySpringData extends PagingAndSortingRepository<ArticoloEntity, Integer> {
    @Query("select a from ArticoloEntity a where a.titolo = :titolo")
    public Optional<ArticoloEntity> findByTitolo(String titolo) throws RepositoryException;
    @Query("select a from ArticoloEntity a join a.sezione s where s.id = :sezioneId")
    List<ArticoloEntity> findAllBySezione(@Param("sezioneId") Integer sezioneId, Pageable pageable);
    @Query("select a from ArticoloEntity a join a.categoria c where c.id = :categoriaId")
    List<ArticoloEntity> findAllByCategoria(@Param("categoriaId") Integer categoriaId, Pageable pageable);
    @Query("select a from ArticoloEntity a join a.sezione s where s.id = :sezioneId")
    List<ArticoloEntity> findAllArticlesByIdSezione(@Param("sezioneId") Integer sezioneId);
    @Query("select a from ArticoloEntity a join a.categoria c where c.id = :categoriaId")
    List<ArticoloEntity> findAllArticlesByIdCategoria(@Param("categoriaId") Integer categoriaId);
    @Query("select a from ArticoloEntity a where a.id = :articoloId")
    Optional<ArticoloEntity> findArticleById(@Param("articoloId") Integer articoloId);
}
