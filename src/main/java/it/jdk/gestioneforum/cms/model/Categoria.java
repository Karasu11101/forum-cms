package it.jdk.gestioneforum.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.jdk.gestioneforum.cms.validation.categoria.annotation.CategoriaExists;
import it.jdk.gestioneforum.cms.validation.categoria.annotation.CategoriaNotExists;
import it.jdk.gestioneforum.cms.validation.categoria.group.CategoriaValidationGroup.CreateValidationGroup;
import it.jdk.gestioneforum.cms.validation.categoria.group.CategoriaValidationGroup.DeleteValidationGroup;
import it.jdk.gestioneforum.cms.validation.categoria.group.CategoriaValidationGroup.UpdateValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria {
    private Integer id;
    private String titolo;
    private String descrizione;
    private List<Articolo> articoli;
    private int versione;

    public Categoria() {}

    @Autowired
    public Categoria(String titolo, String descrizione, List<Articolo> articoli) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.articoli = articoli;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class, DeleteValidationGroup.class})
    @CategoriaNotExists(groups = {CreateValidationGroup.class})
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

    @CategoriaExists(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
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
