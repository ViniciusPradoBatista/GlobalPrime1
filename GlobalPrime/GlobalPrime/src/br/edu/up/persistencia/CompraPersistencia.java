package br.edu.up.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import br.edu.up.entidades.Compra;
import br.edu.up.entidades.Conteudo;
import br.edu.up.entidades.Usuario;

public class CompraPersistencia {
    public static boolean salvar(Compra compra) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.merge(compra);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Compra> buscarComprasPorUsuario(Usuario usuario) {
        EntityManager manager = EntityManagerFactory.getInstance();
        TypedQuery<Compra> query = manager.createQuery(
            "SELECT c FROM Compra c WHERE c.usuario = :usuario", Compra.class);
        query.setParameter("usuario", usuario);
        return query.getResultList();
    }

    public static boolean usuarioComprouConteudo(Usuario usuario, int idConteudo) {
        List<Compra> compras = buscarComprasPorUsuario(usuario);

        for (Compra compra : compras) {
            Conteudo conteudo = compra.getConteudo();
            if (conteudo.getId() == idConteudo) {
                return true;
            }
        }

        return false;
    }
    
    public static boolean remover(Compra compra) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            compra = manager.merge(compra);
            manager.remove(compra);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}