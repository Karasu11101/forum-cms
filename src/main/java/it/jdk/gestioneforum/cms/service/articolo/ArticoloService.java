package it.jdk.gestioneforum.cms.service.articolo;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.$validation.model.ModelValidation;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.repository.articolo.RepositoryArticolo;
import it.jdk.gestioneforum.cms.validation.articolo.group.ArticoloValidationGroup.CreateValidationGroup;
import it.jdk.gestioneforum.cms.validation.articolo.group.ArticoloValidationGroup.DeleteValidationGroup;
import it.jdk.gestioneforum.cms.validation.articolo.group.ArticoloValidationGroup.UpdateValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticoloService implements ArticoloServiceInterface {

    private final RepositoryArticolo repositoryArticolo;
    private final ModelValidation<Articolo, ServiceException> modelValidation;

    @Autowired
    public ArticoloService(RepositoryArticolo repositoryArticolo,
                           ModelValidation<Articolo, ServiceException> modelValidation) {
        this.repositoryArticolo = repositoryArticolo;
        this.modelValidation = modelValidation;
    }

    @Override
    public Articolo createArticolo(Articolo articolo) throws ServiceException {
        modelValidation.validate(articolo, "Impossibile creare l'articolo", CreateValidationGroup.class);
        try {
            return repositoryArticolo.createArticolo(articolo);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Articolo updateArticolo(Articolo articolo) throws ServiceException {
        modelValidation.validate(articolo, "Impossibile modificare l'articolo", UpdateValidationGroup.class);
        try {
            return repositoryArticolo.updateArticolo(articolo);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteArticolo(Articolo articolo) throws ServiceException {
        modelValidation.validate(articolo, "Impossibile cancellare l'articolo", DeleteValidationGroup.class);
        try {
            repositoryArticolo.deleteArticolo(articolo);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Articolo> showArticolo(Integer id) throws ServiceException {
        try {
            return Optional.of(repositoryArticolo.showArticolo(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
