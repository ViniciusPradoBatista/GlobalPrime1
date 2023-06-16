package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Admin;
import br.edu.up.entidades.Filmes;

public class FilmePersistencia {

	public static boolean incluir(Filmes filme) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(filme);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean alterar(Filmes filme) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(filme);
			manager.getTransaction().commit();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean excluir(Filmes filme) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(filme);
			manager.getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Filmes procurarPorNome(Filmes filme) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Filmes where nome = :param");
		consulta.setParameter("param", filme.getNome());
		List<Filmes> filmes = consulta.getResultList();
		if (!filmes.isEmpty()) {
			return filmes.get(0);
		}
		return null;
	}
	
	public static Filmes procurarPorId(int idConteudo) {
	    EntityManager manager = EntityManagerFactory.getInstance();
	    Query consulta = manager.createQuery("from Filmes where id = :param");
	    consulta.setParameter("param", idConteudo);
	    List<Filmes> filmes = consulta.getResultList();
	    if (!filmes.isEmpty()) {
	        return filmes.get(0);
	    }
	    return null;
	}

	public static List<Filmes> getFilmes(Filmes filme) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Filmes where nome like :param");
		consulta.setParameter("param", "%" + filme.getNome() + "%");
		List<Filmes> filmes = consulta.getResultList();
		return filmes;
	}
	
	public static List<Filmes> getFilmes() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("SELECT f FROM Filmes f");
        return consulta.getResultList();
    }


}
