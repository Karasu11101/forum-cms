package it.jdk.gestioneforum.cms.service.sezione;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.$validation.model.ModelValidation;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Sezione;
import it.jdk.gestioneforum.cms.repository.sezione.RepositorySezione;
import it.jdk.gestioneforum.cms.validation.sezione.group.SezioneValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SezioneService implements SezioneServiceInterface {
    private final RepositorySezione repositorySezione;
    private final ModelValidation<Sezione, ServiceException> sezioneValidation;

    @Autowired
    public SezioneService(RepositorySezione repositorySezione,
                          ModelValidation<Sezione, ServiceException> sezioneValidation) {
        this.repositorySezione = repositorySezione;
        this.sezioneValidation = sezioneValidation;
    }

    @Override
    public Sezione createSezione(Sezione sezione) throws ServiceException {
        sezioneValidation.validate(sezione, "Impossibile creare la sezione",
                SezioneValidationGroup.CreateValidationGroup.class);
        try {
            return repositorySezione.createSezione(sezione);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Sezione updateSezione(Sezione sezione) throws ServiceException {
        sezioneValidation.validate(sezione, "Impossibile aggiornare la sezione",
                SezioneValidationGroup.UpdateValidationGroup.class);
        try {
            return repositorySezione.updateSezione(sezione);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteSezione(Sezione sezione) throws ServiceException {
        sezioneValidation.validate(sezione, "Impossibile eliminare la sezione",
                SezioneValidationGroup.DeleteValidationGroup.class);
        try {
            repositorySezione.deleteSezione(sezione);
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Articolo> showArticoli(Integer id) throws ServiceException {
        try {
            return repositorySezione.showArticoli(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Sezione> showSezione(Integer id) throws ServiceException {
        try {
            return Optional.of(repositorySezione.showSezione(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
