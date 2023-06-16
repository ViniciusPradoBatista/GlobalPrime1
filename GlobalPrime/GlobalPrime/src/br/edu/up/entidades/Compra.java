package br.edu.up.entidades;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Filmes filme;

    @ManyToOne
    private Series serie;

    @ManyToOne
    private Documentarios documentario;
    
    private LocalDate dataValidade;
    
    public Compra(Usuario usuario, Conteudo conteudo, LocalDate dataValidade) {
        this.usuario = usuario;
        this.dataValidade = dataValidade;

        if (conteudo instanceof Filmes) {
            this.filme = (Filmes) conteudo;
        } else if (conteudo instanceof Series) {
            this.serie = (Series) conteudo;
        } else if (conteudo instanceof Documentarios) {
            this.documentario = (Documentarios) conteudo;
        }
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    // Construtores, getters e setters

    public Compra() {
    }

    public Compra(Usuario usuario, Filmes filme) {
        this.usuario = usuario;
        this.filme = filme;
    }

    public Compra(Usuario usuario, Series serie) {
        this.usuario = usuario;
        this.serie = serie;
    }

    public Compra(Usuario usuario, Documentarios documentario) {
        this.usuario = usuario;
        this.documentario = documentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Filmes getFilme() {
        return filme;
    }

    public void setFilme(Filmes filme) {
        this.filme = filme;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }

    public Documentarios getDocumentario() {
        return documentario;
    }

    public void setDocumentario(Documentarios documentario) {
        this.documentario = documentario;
    }
    
    public Conteudo getConteudo() {
        if (filme != null) {
            return (Conteudo) filme;
        } else if (serie != null) {
            return (Conteudo) serie;
        } else if (documentario != null) {
            return (Conteudo) documentario;
        }
        return null;
    }
}
