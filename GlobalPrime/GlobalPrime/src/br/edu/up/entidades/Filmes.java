package br.edu.up.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "filmes")
public class Filmes extends Conteudo {
    @Column(name = "tipo")
    private String tipo; // Propriedade para representar o tipo de conteúdo (filme, série, etc.)
    
    private String diretor;

    // Construtores, getters e setters

    public Filmes() {
        super(); // Chama o construtor da superclasse
        this.tipo = "filme"; // Define o tipo como "filme" por padrão
    }

    public Filmes(int id, String nome, String duracao, String classificacao, String categoria,
                  int anoEstreia, String diretor, double preco) {
        super(id, nome, duracao, classificacao, categoria, anoEstreia, preco);
        this.setDiretor(diretor);
        this.tipo = "filme"; // Define o tipo como "filme" por padrão
    }

    // Getter e setter para o tipo de conteúdo

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
}
