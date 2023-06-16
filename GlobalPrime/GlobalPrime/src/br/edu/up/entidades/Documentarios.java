package br.edu.up.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "documentarios")
public class Documentarios extends Conteudo {
    @Column(name = "tipo")
    private String tipo; // Propriedade para representar o tipo de conteúdo (filme, série, etc.)
    
    private String diretor;

    // Construtores, getters e setters

    public Documentarios() {
        super(); // Chama o construtor da superclasse
        this.tipo = "documentário"; // Define o tipo como "documentário" por padrão
    }

    public Documentarios(int id, String nome, String duracao, String classificacao, String categoria,
                         int anoEstreia, double preco, String diretor) {
        super(id, nome, duracao, classificacao, categoria, anoEstreia, preco);
        this.diretor = diretor;
        this.tipo = "documentário"; // Define o tipo como "documentário" por padrão
    }

    // Getter e setter para o tipo de conteúdo

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter e setter específico para a classe Documentarios

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
}
