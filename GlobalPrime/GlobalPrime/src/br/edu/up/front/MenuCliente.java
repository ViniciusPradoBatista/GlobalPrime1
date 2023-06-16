package br.edu.up.front;

import java.text.DecimalFormat;
import java.util.List;

import br.edu.up.entidades.Compra;
import br.edu.up.entidades.Conteudo;
import br.edu.up.entidades.Documentarios;
import br.edu.up.entidades.Filmes;
import br.edu.up.entidades.Series;
import br.edu.up.entidades.Usuario;
import br.edu.up.negocio.UsuarioNegocio;
import br.edu.up.persistencia.CompraPersistencia;
import br.edu.up.persistencia.DocumentarioPersistencia;
import br.edu.up.persistencia.FilmePersistencia;
import br.edu.up.persistencia.SeriePersistencia;
import br.edu.up.persistencia.UsuarioPersistencia;

public class MenuCliente {
	private Usuario usuarioLogado;
	int opc;
	
    public MenuCliente(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

	public void exibirMenu() {
		do {
			System.out.println("\n*** MENU CLIENTE ***");
			System.out.println("\n1 - Minha Conta");
			System.out.println("2 - Lista de conteúdo");
			System.out.println("3 - Escolher Filme, Série ou Documentário");
			System.out.println("4 - Deslogar");
			opc = Console.readInt("Informe a opção: ");

			switch (opc) {
			case 1:
				do {
					System.out.println("\n*** MINHA CONTA ***");
					System.out.println("\n1 - Consultar conta (Saldo)");
					System.out.println("2 - Consultar itens em minha conta");
					System.out.println("3 - Depositar");
					System.out.println("4 - Alterar conta");
					System.out.println("5 - Excluir conta");
					System.out.println("6 - Voltar");
					opc = Console.readInt("Opção: ");
					switch (opc) {
					case 1:
						saldoCliente();
						break;
					case 2:
						consultaCliente();
						break;
					case 3:
						depositarCliente();
						break;
					case 4:
						altCliente();
						break;
					case 5:
						exclusaoCliente();
						break;
					}
				} while (opc != 6);
				break;

			case 2:

				System.out.println("\nLISTA DE SÉRIES:");
				List<Series> series = SeriePersistencia.getSeries();
				if (series.isEmpty()) {
					System.out.println("Nenhuma série encontrada.");
				} else {
					for (Series serie : series) {
						System.out.println("ID: " + serie.getId());
						System.out.println("Nome: " + serie.getNome());
						System.out.println("Categoria: " + serie.getCategoria());
						System.out.println("Ano estreia: " + serie.getAnoEstreia());
						System.out.println("Classificação: " + serie.getClassificacao());
						System.out.println("Criador: " + serie.getCriador());
						System.out.println("Preço: " + serie.getPreco());
						System.out.println("---------------------------");
					}
				}

				System.out.println("\nLISTA DE FILMES:");
				List<Filmes> filmes = FilmePersistencia.getFilmes();
				if (filmes.isEmpty()) {
					System.out.println("Nenhum filme encontrado.");
				} else {
					for (Filmes filme : filmes) {
						System.out.println("ID: " + filme.getId());
						System.out.println("Nome: " + filme.getNome());
						System.out.println("Categoria: " + filme.getCategoria());
						System.out.println("Ano estreia: " + filme.getAnoEstreia());
						System.out.println("Classificação: " + filme.getClassificacao());
						System.out.println("Diretor: " + filme.getDiretor());
						System.out.println("Preço: " + filme.getPreco());
						System.out.println("---------------------------");
					}
				}

				System.out.println("\nLISTA DE DOCUMENTÁRIOS:");
				List<Documentarios> documentarios = DocumentarioPersistencia.getDoc();
				if (documentarios.isEmpty()) {
					System.out.println("Nenhum documentário encontrado.");
				} else {
					for (Documentarios documentario : documentarios) {
						System.out.println("ID: " + documentario.getId());
						System.out.println("Nome: " + documentario.getNome());
						System.out.println("Categoria: " + documentario.getCategoria());
						System.out.println("Ano estreia: " + documentario.getAnoEstreia());
						System.out.println("Classificação: " + documentario.getClassificacao());
						System.out.println("Diretor: " + documentario.getDiretor());
						System.out.println("Preço: " + documentario.getPreco());
						System.out.println("---------------------------");
					}
				}

				break;

			case 3:
				
				int idConteudo = Console.readInt("Informe o ID do conteúdo que deseja comprar: ");
			    UsuarioNegocio.comprarConteudo(usuarioLogado, idConteudo);
			    break;
				
				

			}
		} while (opc != 4);
	}

	private void saldoCliente() {
	    System.out.println("\n*** SALDO DA SUA CONTA ***");
	    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	    String saldoFormatado = decimalFormat.format(usuarioLogado.getConta());
	    System.out.println("\nSua conta apresenta R$ " + saldoFormatado + " de saldo para compra.");
	}

	private void consultaCliente() {
	    System.out.println("\n*** ITENS EM MINHA CONTA ***");

	    List<Compra> compras = CompraPersistencia.buscarComprasPorUsuario(usuarioLogado);
	    if (compras.isEmpty()) {
	        System.out.println("Nenhum item encontrado em sua conta.");
	    } else {
	        for (Compra compra : compras) {
	            System.out.println("ID da Compra: " + compra.getId());

	            Conteudo conteudo = compra.getConteudo();
	            System.out.println("ID do Conteúdo: " + conteudo.getId());
	            System.out.println("Nome: " + conteudo.getNome());
	            System.out.println("Tipo: " + conteudo.getTipo());
	            System.out.println("---------------------------");

	            // Salvar os resultados no banco de dados
	            CompraPersistencia.salvar(compra);
	        }
	    }
	}

	private void depositarCliente() {
		double valor = Console.readDouble("Informe o valor a ser depositado: ");

	    if (valor > 0) {
	        UsuarioNegocio.depositar(usuarioLogado, valor);
	        System.out.println("Depósito realizado com sucesso!");
	        System.out.println("Novo saldo: R$ " + usuarioLogado.getConta());
	    } else {
	        System.out.println("Valor inválido. O valor do depósito deve ser maior que zero.");
	    }

	}

	private void altCliente() {
		System.out.println("\n*** ALTERAÇÃO DE CLIENTES ***");
		System.out.println("\nDados do cliente logado:");
		System.out.println("-------------------------");
		System.out.println("ID: " + usuarioLogado.getId());
		System.out.println("Nome do cliente: " + usuarioLogado.getNome());
		System.out.println("Telefone do cliente: " + usuarioLogado.getTelefone());
		System.out.println("Email: " + usuarioLogado.getEmail());
		System.out.println("CPF: " + usuarioLogado.getCpf());
		System.out.println("-------------------------");

		String resp = Console.readString("Quer alterar seus dados? (S/N) ");
		if (resp.equalsIgnoreCase("S")) {
			usuarioLogado.setNome(Console.readString("Informe o novo nome: "));
			usuarioLogado.setTelefone(Console.readInt("Informe o novo telefone: "));
			usuarioLogado.setEmail(Console.readString("Informe o novo email: "));

			if (UsuarioPersistencia.alterar(usuarioLogado)) {
				System.out.println("Os dados foram atualizados com sucesso!");
			} else {
				System.out.println("Não foi possível atualizar os dados no momento.");
			}
		}
	}

	private void exclusaoCliente() {
		System.out.println("\n*** EXCLUSÃO DE CONTA CLIENTE ***");
		System.out.println("\nDados do cliente logado:");
		System.out.println("-------------------------");
		System.out.println("ID: " + usuarioLogado.getId());
		System.out.println("Nome do cliente: " + usuarioLogado.getNome());
		System.out.println("Telefone do cliente: " + usuarioLogado.getTelefone());
		System.out.println("Email: " + usuarioLogado.getEmail());
		System.out.println("CPF: " + usuarioLogado.getCpf());
		System.out.println("-------------------------");

		String resp = Console.readString("Quer excluir sua conta? (S/N) ");
		if (resp.equalsIgnoreCase("S")) {
			if (UsuarioPersistencia.excluir(usuarioLogado)) {
				System.out.println("A exclusão foi realizada com sucesso...");
			} else {
				System.out.println("A exclusão não pôde ser realizada no momento...");
			}
		}
	}
}
