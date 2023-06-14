package it.jdk.gestioneforum.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.jdk.gestioneforum.cms.validation.sezione.annotation.SezioneExists;
import it.jdk.gestioneforum.cms.validation.sezione.annotation.SezioneNotExists;
import it.jdk.gestioneforum.cms.validation.sezione.group.SezioneValidationGroup.CreateValidationGroup;
import it.jdk.gestioneforum.cms.validation.sezione.group.SezioneValidationGroup.DeleteValidationGroup;
import it.jdk.gestioneforum.cms.validation.sezione.group.SezioneValidationGroup.UpdateValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sezione {

    private Integer id;
    private String titolo;
    private String descrizione;
    private List<Articolo> articoli;
    private int versione;

    public Sezione() {}

    @Autowired
    public Sezione(String titolo, String descrizione, List<Articolo> articoli) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.articoli = articoli;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    @SezioneNotExists(groups = {CreateValidationGroup.class})
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    @SezioneExists(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
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
