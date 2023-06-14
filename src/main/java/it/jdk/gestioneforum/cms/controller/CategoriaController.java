package it.jdk.gestioneforum.cms.controller;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.model.Articolo;
import it.jdk.gestioneforum.cms.model.Categoria;
import it.jdk.gestioneforum.cms.service.categoria.CategoriaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaServiceInterface categoriaServiceInterface;

    @Autowired
    public CategoriaController(CategoriaServiceInterface categoriaServiceInterface) {
        this.categoriaServiceInterface = categoriaServiceInterface;
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Categoria> create
            (@RequestBody Categoria categoria) throws ServiceException {
        Categoria categoriaR = categoriaServiceInterface.createCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaR);
    }

    @PatchMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Categoria> update
            (@RequestBody Categoria categoria) throws ServiceException {
        Categoria categoriaR = categoriaServiceInterface.updateCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaR);
    }

    @DeleteMapping(
            value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Categoria> delete
            (@RequestBody Categoria categoria) throws ServiceException {
        categoriaServiceInterface.deleteCategoria(categoria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(categoria);
    }

    @GetMapping(
            value = "/show/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<Categoria>> showCategoria
            (@PathVariable Integer id) throws ServiceException {
        Optional<Categoria> categoriaR = categoriaServiceInterface.showCategoria(id);
        if(categoriaR.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaR);
        } else
            throw new ServiceException("Articolo non trovato");
    }

    @GetMapping(
            value = "/showArticoli/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Articolo>> showArticoli
            (@PathVariable Integer id) throws ServiceException {
        List<Articolo> articoliR = categoriaServiceInterface.showArticoli(id);
        return ResponseEntity.status(HttpStatus.OK).body(articoliR);
    }
}
