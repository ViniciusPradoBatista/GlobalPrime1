package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Documentarios;
import br.edu.up.entidades.Series;

public class DocumentarioPersistencia {
	
	public static boolean incluir(Documentarios documentario) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(documentario);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean alterar(Documentarios documentario) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(documentario);
			manager.getTransaction().commit();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean excluir(Documentarios documentario) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(documentario);
			manager.getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Documentarios procurarPorNome(Documentarios documentario) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Documentarios where nome = :param");
		consulta.setParameter("param", documentario.getNome());
		List<Documentarios> documentarios = consulta.getResultList();
		if (!documentarios.isEmpty()) {
			return documentarios.get(0);
		}
		return null;
	}
	
	public static Documentarios procurarPorId(Integer idConteudo) {
	    EntityManager manager = EntityManagerFactory.getInstance();
	    Query consulta = manager.createQuery("from Documentarios where id = :param");
	    consulta.setParameter("param", idConteudo);
	    List<Documentarios> documentarios = consulta.getResultList();
	    if (!documentarios.isEmpty()) {
	        return documentarios.get(0);
	    }
	    return null;
	}

	public static List<Documentarios> getDoc(Documentarios documentario) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Documentarios where nome like :param");
		consulta.setParameter("param", "%" + documentario.getNome() + "%");
		List<Documentarios> documentarios = consulta.getResultList();
		return documentarios;
	}
	
	public static List<Documentarios> getDoc() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("SELECT f FROM Documentarios f");
        return consulta.getResultList();
    }




}
