package br.edu.up.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "series")
public class Series extends Conteudo {
    @Column(name = "tipo")
    private String tipo; // Propriedade para representar o tipo de conteúdo (filme, série, etc.)
    
    private String criador;

    // Construtores, getters e setters

    public Series() {
        super(); // Chama o construtor da superclasse
        this.tipo = "série"; // Define o tipo como "série" por padrão
    }

    public Series(int id, String nome, String duracao, String classificacao, String categoria,
                  int anoEstreia, String criador, double preco) {
        super(id, nome, duracao, classificacao, categoria, anoEstreia, preco);
        this.criador = criador;
        this.tipo = "série"; // Define o tipo como "série" por padrão
    }

    // Getter e setter para o tipo de conteúdo

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter e setter específico para a classe Series

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }
}
