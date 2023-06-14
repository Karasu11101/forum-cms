package it.jdk.gestioneforum.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.jdk.gestioneforum.cms.validation.articolo.group.ArticoloValidationGroup.CreateValidationGroup;
import it.jdk.gestioneforum.cms.validation.articolo.group.ArticoloValidationGroup.DeleteValidationGroup;
import it.jdk.gestioneforum.cms.validation.articolo.group.ArticoloValidationGroup.UpdateValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Articolo {
    private Integer id;
    private String titolo;
    private String data;
    private String testo;
    private Sezione sezione;
    private Categoria categoria;
    private int versione;

    public Articolo() {}

    @Autowired
    public Articolo(String titolo, String testo, Sezione sezione, Categoria categoria) {
        this.titolo = titolo;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        this.data = zonedDateTime.format(formatter);
        this.testo = testo;
        this.sezione = sezione;
        this.categoria = categoria;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
//    @ArticoloExists(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
//    @ArticoloNotExists(groups = {CreateValidationGroup.class})
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
//    @SezioneExists(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    public Sezione getSezione() {
        return sezione;
    }

    public void setSezione(Sezione sezione) {
        this.sezione = sezione;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
//    @CategoriaExists(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @NotNull(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotNull(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
    public int getVersione() {
        return versione;
    }

    public void setVersione(int versione) {
        this.versione = versione;
    }
}
