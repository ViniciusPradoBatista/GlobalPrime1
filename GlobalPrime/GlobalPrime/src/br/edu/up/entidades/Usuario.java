package br.edu.up.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String cpf;
	private String senha;
	private int telefone;
	private String email;
	private double conta;
	
	@OneToMany(mappedBy = "usuario")
	private List<Conteudo> itensComprados;
	 
	 public List<Conteudo> getItensComprados() {
	        return itensComprados;
	    }
	 
	 public void setItensComprados(List<Conteudo> itensComprados) {
	        this.itensComprados = itensComprados;
	    }
	 
	 public void removerConteudo(Conteudo conteudo) {
		    if (itensComprados != null) {
		        itensComprados.remove(conteudo);
		        conteudo.setUsuario(null);
		    }
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getConta() {
		return conta;
	}
	public void setConta(double d) {
		this.conta = d;
	}

	
	
}