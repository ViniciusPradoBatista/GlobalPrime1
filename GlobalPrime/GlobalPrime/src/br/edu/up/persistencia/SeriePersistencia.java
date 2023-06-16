package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Filmes;
import br.edu.up.entidades.Series;

public class SeriePersistencia {
	public static boolean incluir(Series serie) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(serie);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean alterar(Series serie) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(serie);
			manager.getTransaction().commit();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean excluir(Series serie) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(serie);
			manager.getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Series procurarPorNome(Series serie) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Series where nome = :param");
		consulta.setParameter("param", serie.getNome());
		List<Series> series = consulta.getResultList();
		if (!series.isEmpty()) {
			return series.get(0);
		}
		return null;
	}
	
	public static Series procurarPorId(Integer idConteudo) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Series where id = :param");
		consulta.setParameter("param", idConteudo);
		List<Series> series = consulta.getResultList();
		if (!series.isEmpty()) {
			return series.get(0);
		}
		return null;
	}

	public static List<Series> getSeries(Series serie) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Series where nome like :param");
		consulta.setParameter("param", "%" + serie.getNome() + "%");
		List<Series> series = consulta.getResultList();
		return series;
	}
	
	 public static List<Series> getSeries() {
	        EntityManager manager = EntityManagerFactory.getInstance();
	        Query consulta = manager.createQuery("SELECT s FROM Series s");
	        return consulta.getResultList();
	    }

}
