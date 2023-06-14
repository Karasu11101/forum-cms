package it.jdk.gestioneforum.cms.service.sezione;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Sezione;

import java.util.List;
import java.util.Optional;

public interface SezioneServiceInterface {
    public Sezione createSezione(Sezione sezione) throws ServiceException;
    public Sezione updateSezione(Sezione sezione) throws ServiceException;
    public void deleteSezione(Sezione sezione) throws ServiceException;
    public List<Articolo> showArticoli(String titoloSezione) throws ServiceException;
    public Optional<Sezione> showSezione(String titolo) throws ServiceException;
}
