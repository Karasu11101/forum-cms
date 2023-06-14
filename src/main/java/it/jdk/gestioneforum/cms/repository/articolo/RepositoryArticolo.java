package it.jdk.gestioneforum.cms.repository.articolo;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryArticolo {
    public Articolo createArticolo(Articolo articolo) throws RepositoryException;
    public Articolo updateArticolo(Articolo articolo) throws RepositoryException;
    public void deleteArticolo(Articolo articolo) throws RepositoryException;
    public Articolo showArticolo(Integer id) throws RepositoryException;
}
