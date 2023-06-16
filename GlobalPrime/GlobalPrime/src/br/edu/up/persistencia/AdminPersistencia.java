package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.edu.up.entidades.Admin;

public class AdminPersistencia {
	
	public static boolean incluir(Admin admin){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(admin);
			manager.getTransaction().commit();
			return true;			
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static Admin procurarPorCPF(Admin admin){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Admin where cpf = :param");
		consulta.setParameter("param", admin.getCpf());
		List<Admin> admins = consulta.getResultList();
		if(!admins.isEmpty()){
			return admins.get(0);
		}
		return null;
	}
	
	public static boolean alterar(Admin admin){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(admin);
			manager.getTransaction().commit();
			return true;			
			
		}
		catch(Exception e){
			return false;
		}
	}

	
	public static boolean excluir(Admin admin){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(admin);
			manager.getTransaction().commit();
			return true;			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static Admin procurarPorId(Admin admin) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Admin where id = :param");
		consulta.setParameter("param", admin.getId());
		List<Admin> admins = consulta.getResultList();
		if (!admins.isEmpty()) {
			return admins.get(0);
		}
		return null;
	}
	
	public static Admin procurarPorNome(Admin admin){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Admin where nome = :param");
		consulta.setParameter("param", admin.getNome());
		List<Admin> admins = consulta.getResultList();
		if(!admins.isEmpty()){
			return admins.get(0);
		}
		return null;
	}
	
	public static List<Admin> getAdmins(Admin admin){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Admin where nome like :param");
		consulta.setParameter("param", "%" + admin.getNome() + "%");
		List<Admin> admins = consulta.getResultList();
		return admins;
	}
	
	public static boolean validarSenha(Admin admin, String senha) {
	    EntityManager manager = EntityManagerFactory.getInstance();
	    Query consulta = manager.createQuery("from Admin where id = :param");
	    consulta.setParameter("param", admin.getId());
	    List<Admin> admins = consulta.getResultList();
	    if (!admins.isEmpty()) {
	        Admin adminEncontrado = admins.get(0);
	        return adminEncontrado.getSenha().equals(senha);
	    }
	    return false;
	}
	
}
