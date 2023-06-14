package it.jdk.gestioneforum.cms.controller;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.service.articolo.ArticoloServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articolo")
public class ArticoloController {

    private final ArticoloServiceInterface articoloService;

    @Autowired
    public ArticoloController(ArticoloServiceInterface articoloService) {
        this.articoloService = articoloService;
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Articolo> create
            (@RequestBody Articolo articolo) throws ServiceException {
        Articolo articoloR = articoloService.createArticolo(articolo);
        return ResponseEntity.status(HttpStatus.CREATED).body(articoloR);
    }

    @PatchMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Articolo> update
            (@RequestBody Articolo articolo) throws ServiceException {
        Articolo articoloR = articoloService.updateArticolo(articolo);
        return ResponseEntity.status(HttpStatus.OK).body(articoloR);
    }

    @DeleteMapping(
            value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Articolo> delete
            (@RequestBody Articolo articolo) throws ServiceException {
        articoloService.deleteArticolo(articolo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(articolo);
    }

    @GetMapping(
            value = "/show/{titolo}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<Articolo>> showArticolo
            (@PathVariable String titolo) throws ServiceException {
        Optional<Articolo> articoloR = articoloService.showArticolo(titolo);
        if(articoloR.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(articoloR);
        } else
            throw new ServiceException("Articolo non trovato");
    }
}
