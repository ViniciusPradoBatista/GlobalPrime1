package br.edu.up.negocio;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import br.edu.up.entidades.Compra;
import br.edu.up.entidades.Conteudo;
import br.edu.up.entidades.Documentarios;
import br.edu.up.entidades.Filmes;
import br.edu.up.entidades.Series;
import br.edu.up.entidades.Usuario;
import br.edu.up.persistencia.CompraPersistencia;
import br.edu.up.persistencia.DocumentarioPersistencia;
import br.edu.up.persistencia.FilmePersistencia;
import br.edu.up.persistencia.SeriePersistencia;


public class UsuarioNegocio {

	public static void depositar(Usuario usuario, double valor) {
		if (valor > 0) {
			usuario.setConta(usuario.getConta() + valor);
		}
	}
	
	public static boolean usuarioComprouConteudo(Usuario usuario, int idConteudo) {
	    List<Compra> compras = CompraPersistencia.buscarComprasPorUsuario(usuario);

	    for (Compra compra : compras) {
	        Conteudo conteudo = compra.getConteudo();
	        if (conteudo.getId() == idConteudo) {
	            return true;
	        }
	    }

	    return false;
	}

	public static void comprarConteudo(Usuario usuario, int idConteudo) {
	    
	    if (usuarioComprouConteudo(usuario, idConteudo)) {
	        System.out.println("Você já comprou esse conteúdo anteriormente.");
	        return;
	    }

	    Filmes filme = FilmePersistencia.procurarPorId(idConteudo);
	    Series serie = SeriePersistencia.procurarPorId(idConteudo);
	    Documentarios documentario = DocumentarioPersistencia.procurarPorId(idConteudo);

	    if (filme != null) {
	        comprarFilme(usuario, filme);
	    } else if (serie != null) {
	        comprarSerie(usuario, serie);
	    } else if (documentario != null) {
	        comprarDocumentario(usuario, documentario);
	    } else {
	        System.out.println("Nenhum conteúdo encontrado com o ID informado.");
	    }
	}

    private static void comprarFilme(Usuario usuario, Filmes filme) {
        double precoFilme = filme.getPreco();

        System.out.println("Deseja comprar o filme '" + filme.getNome() + "'? (S/N)");
        Scanner scanner = new Scanner(System.in);
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            if (usuario.getConta() >= precoFilme) {
                usuario.setConta(usuario.getConta() - precoFilme);
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String novoSaldo = decimalFormat.format(usuario.getConta());
                System.out.println("\nFilme '" + filme.getNome() + "' comprado com sucesso!");
                System.out.println("Novo saldo: R$ " + novoSaldo);

                // Criar instância de Compra
                LocalDate dataValidade = LocalDate.now().plusWeeks(1);
                Compra compra = new Compra(usuario, filme, dataValidade);
                CompraPersistencia.salvar(compra);

            } else {
                System.out.println("Saldo insuficiente para comprar o filme.");
            }
        } else {
            System.out.println("Compra do filme '" + filme.getNome() + "' cancelada.");
        }
    }

    private static void comprarSerie(Usuario usuario, Series serie) {
        double precoSerie = serie.getPreco();

        System.out.println("Deseja comprar a série '" + serie.getNome() + "'? (S/N)");
        Scanner scanner = new Scanner(System.in);
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            if (usuario.getConta() >= precoSerie) {
                usuario.setConta(usuario.getConta() - precoSerie);
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String novoSaldo = decimalFormat.format(usuario.getConta());
                System.out.println("\nSérie '" + serie.getNome() + "' comprada com sucesso!");
                System.out.println("Novo saldo: R$ " + novoSaldo);

                // Criar instância de Compra
                LocalDate dataValidade = LocalDate.now().plusWeeks(1);
                Compra compra = new Compra(usuario, serie, dataValidade);
                CompraPersistencia.salvar(compra);

            } else {
                System.out.println("Saldo insuficiente para comprar a série.");
            }
        } else {
            System.out.println("Compra da série '" + serie.getNome() + "' cancelada.");
        }
    }

    private static void comprarDocumentario(Usuario usuario, Documentarios documentario) {
        double precoDocumentario = documentario.getPreco();

        System.out.println("Deseja comprar o documentário '" + documentario.getNome() + "'? (S/N)");
        Scanner scanner = new Scanner(System.in);
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            if (usuario.getConta() >= precoDocumentario) {
                usuario.setConta(usuario.getConta() - precoDocumentario);
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String novoSaldo = decimalFormat.format(usuario.getConta());
                System.out.println("\nDocumentário '" + documentario.getNome() + "' comprado com sucesso!");
                System.out.println("Novo saldo: R$ " + novoSaldo);

                // Criar instância de Compra
                LocalDate dataValidade = LocalDate.now().plusWeeks(1);
                Compra compra = new Compra(usuario, documentario, dataValidade);
                CompraPersistencia.salvar(compra);

            } else {
                System.out.println("Saldo insuficiente para comprar o documentário.");
            }
        } else {
            System.out.println("Compra do documentário '" + documentario.getNome() + "' cancelada.");
        }
    }

}
