package it.jdk.gestioneforum.cms.repository.categoria;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositoryCategoria")
public interface RepositoryCategoria {
    public Categoria createCategoria(Categoria categoria) throws RepositoryException;
    public Categoria updateCategoria(Categoria categoria) throws RepositoryException;
    public void deleteCategoria(Categoria categoria) throws RepositoryException;
    public List<Articolo> showArticoli(Integer id) throws RepositoryException;
    public Categoria showCategoria(Integer categoria_id) throws RepositoryException;
}
