package it.jdk.gestioneforum.cms.controller;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Sezione;
import it.jdk.gestioneforum.cms.service.sezione.SezioneServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sezione")
public class SezioneController {

    private final SezioneServiceInterface sezioneServiceInterface;

    @Autowired
    public SezioneController(SezioneServiceInterface sezioneServiceInterface) {
        this.sezioneServiceInterface = sezioneServiceInterface;
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Sezione> create
            (@RequestBody Sezione sezione) throws ServiceException {
        Sezione sezioneR = sezioneServiceInterface.createSezione(sezione);
        return ResponseEntity.status(HttpStatus.CREATED).body(sezioneR);
    }

    @PatchMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Sezione> update
            (@RequestBody Sezione sezione) throws ServiceException {
        Sezione sezioneR = sezioneServiceInterface.updateSezione(sezione);
        return ResponseEntity.status(HttpStatus.OK).body(sezioneR);
    }

    @DeleteMapping(
            value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Sezione> delete
            (@RequestBody Sezione sezione) throws ServiceException {
        sezioneServiceInterface.deleteSezione(sezione);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sezione);
    }

    @GetMapping(
            value = "/show/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<Sezione>> showSezione
            (@PathVariable Integer id) throws ServiceException {
        Optional<Sezione> sezioneR = sezioneServiceInterface.showSezione(id);
        if(sezioneR.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(sezioneR);
        } else
            throw new ServiceException("Articolo non trovato");
    }

    @GetMapping(
            value = "/showArticoli/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Articolo>> showArticoli
            (@PathVariable Integer id) throws ServiceException {
        List<Articolo> articoliR = sezioneServiceInterface.showArticoli(id);
        return ResponseEntity.status(HttpStatus.OK).body(articoliR);
    }
}
