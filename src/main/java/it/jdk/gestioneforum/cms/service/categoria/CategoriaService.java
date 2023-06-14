package it.jdk.gestioneforum.cms.service.categoria;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.$validation.model.ModelValidation;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;
import it.jdk.gestioneforum.cms.repository.categoria.RepositoryCategoria;
import it.jdk.gestioneforum.cms.validation.categoria.group.CategoriaValidationGroup.CreateValidationGroup;
import it.jdk.gestioneforum.cms.validation.categoria.group.CategoriaValidationGroup.DeleteValidationGroup;
import it.jdk.gestioneforum.cms.validation.categoria.group.CategoriaValidationGroup.UpdateValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements CategoriaServiceInterface {
    private final RepositoryCategoria repositoryCategoria;
    private final ModelValidation<Categoria, ServiceException> categoriaValidation;
    @Autowired
    public CategoriaService(RepositoryCategoria repositoryCategoria,
                            ModelValidation<Categoria, ServiceException> categoriaValidation) {
        this.repositoryCategoria = repositoryCategoria;
        this.categoriaValidation = categoriaValidation;
    }

    @Override
    public Categoria createCategoria(Categoria categoria) throws ServiceException {
        categoriaValidation.validate(categoria, "Impossibile creare la categoria",
                CreateValidationGroup.class);
        try {
            repositoryCategoria.createCategoria(categoria);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return categoria;
    }

    public Categoria updateCategoria(Categoria categoria) throws ServiceException {
        categoriaValidation.validate(categoria, "Impossibile aggiornare la categoria",
                UpdateValidationGroup.class);
        try {
            repositoryCategoria.updateCategoria(categoria);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return categoria;
    }

    @Override
    public void deleteCategoria(Categoria categoria) throws ServiceException {
        categoriaValidation.validate(categoria, "Impossibile eliminare la categoria",
                DeleteValidationGroup.class);
        try {
            repositoryCategoria.deleteCategoria(categoria);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Articolo> showArticoli(Integer id) throws ServiceException {
        try {
            return repositoryCategoria.showArticoli(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Categoria> showCategoria(Integer id) throws ServiceException {
        try {
            return Optional.of(repositoryCategoria.showCategoria(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
