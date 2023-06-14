package it.jdk.gestioneforum.cms.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
@SequenceGenerator(name = "cms_generator", sequenceName = "cms_generator", initialValue = 1, allocationSize = 4)
public class CategoriaEntity {

    private Integer id;
    private String titolo;
    private String descrizione;
    private List<ArticoloEntity> articoli;
    private int versione;

    @Id
    @GeneratedValue(generator = "cms_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_categoria")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "titolo")
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Column(name = "descrizione")
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, orphanRemoval = false)
    public List<ArticoloEntity> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<ArticoloEntity> articoli) {
        this.articoli = articoli;
    }

    @Version
    @Column(name = "versione")
    public int getVersione() {
        return versione;
    }

    public void setVersione(int versione) {
        this.versione = versione;
    }
}
