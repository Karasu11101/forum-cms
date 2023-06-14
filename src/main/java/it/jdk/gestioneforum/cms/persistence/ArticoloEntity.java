package it.jdk.gestioneforum.cms.persistence;

import javax.persistence.*;
@Entity
@Table(name = "articolo")
@SequenceGenerator(name = "cms_generator", sequenceName = "cms_generator", initialValue = 1, allocationSize = 4)
public class ArticoloEntity {
    private Integer id;
    private String titolo;
    private String data;
    private String testo;
    private SezioneEntity sezione;
    private CategoriaEntity categoria;
    private int versione;

    @Id
    @GeneratedValue(generator = "cms_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_articolo")
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

    @Column(name = "data")
    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Column(name = "testo")
    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sezione")
    public SezioneEntity getSezione() {
        return sezione;
    }

    public void setSezione(SezioneEntity sezione) {
        this.sezione = sezione;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
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
