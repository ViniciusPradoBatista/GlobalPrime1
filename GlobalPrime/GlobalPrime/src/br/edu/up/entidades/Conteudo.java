package br.edu.up.entidades;

import javax.persistence.*;

@Entity
@Table(name = "conteudos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conteudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String duracao;
    private String classificacao;
    private String categoria;
    private int anoEstreia;
    private double preco;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "tipo")
    private String tipo; // Propriedade para representar o tipo de conteúdo (filme, série, etc.)

    @Column(name = "diretor")
    private String diretor; // Propriedade para representar o diretor do conteúdo

    @Column(name = "criador")
    private String criador; // Propriedade para representar o criador do conteúdo

    // Construtores, getters e setters

    public Conteudo() {
        this.preco = 0; // Definindo o valor padrão para o preço
    }

    public Conteudo(int id, String nome, String duracao, String classificacao, String categoria,
                    int anoEstreia, double preco) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.classificacao = classificacao;
        this.categoria = categoria;
        this.anoEstreia = anoEstreia;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnoEstreia() {
        return anoEstreia;
    }

    public void setAnoEstreia(int anoEstreia) {
        this.anoEstreia = anoEstreia;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }
}
