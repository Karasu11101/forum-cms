package it.jdk.gestioneforum.cms.repository.sezione;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Sezione;

import java.util.List;


public interface RepositorySezione {
    public Sezione createSezione(Sezione sezione) throws RepositoryException;
    public Sezione updateSezione(Sezione sezione) throws RepositoryException;
    public void deleteSezione(Sezione sezione) throws RepositoryException;
    public List<Articolo> showArticoli(Integer id) throws RepositoryException;
    public Sezione showSezione(Integer id) throws RepositoryException;
}
