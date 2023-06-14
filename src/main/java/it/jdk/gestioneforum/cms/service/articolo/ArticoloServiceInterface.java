package it.jdk.gestioneforum.cms.service.articolo;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.model.Articolo;

import java.util.Optional;

public interface ArticoloServiceInterface {
    public Articolo createArticolo(Articolo articolo) throws ServiceException;
    public Articolo updateArticolo(Articolo articolo) throws ServiceException;
    public void deleteArticolo(Articolo articolo) throws ServiceException;
    public Optional<Articolo> showArticolo(Integer id) throws ServiceException;
}
