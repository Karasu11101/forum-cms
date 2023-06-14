package it.jdk.gestioneforum.cms.service.categoria;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaServiceInterface {
    public Categoria createCategoria(Categoria categoria) throws ServiceException;
    public Categoria updateCategoria(Categoria categoria) throws ServiceException;
    public void deleteCategoria(Categoria categoria) throws ServiceException;
    public List<Articolo> showArticoli(Integer id) throws ServiceException;
    public Optional<Categoria> showCategoria(Integer id) throws ServiceException;
}
