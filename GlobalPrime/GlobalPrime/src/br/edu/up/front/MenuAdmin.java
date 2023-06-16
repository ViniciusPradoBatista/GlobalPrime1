package br.edu.up.front;

import java.time.LocalDate;
import java.util.List;

import br.edu.up.entidades.Admin;
import br.edu.up.entidades.Compra;
import br.edu.up.entidades.Documentarios;
import br.edu.up.entidades.Filmes;
import br.edu.up.entidades.Series;
import br.edu.up.entidades.Usuario;
import br.edu.up.persistencia.AdminPersistencia;
import br.edu.up.persistencia.CompraPersistencia;
import br.edu.up.persistencia.DocumentarioPersistencia;
import br.edu.up.persistencia.FilmePersistencia;
import br.edu.up.persistencia.SeriePersistencia;
import br.edu.up.persistencia.UsuarioPersistencia;

public class MenuAdmin {
	private Admin adminLogado;
	int opc;

	public MenuAdmin(Admin adminLogado) {
		this.adminLogado = adminLogado;

	}

	public void exibirMenu() {

		do {
			System.out.println("\n*** MENU ADMIN ***");
			System.out.println("\n1 - Configurar conta ADMIN");
			System.out.println("2 - Consultar clientes");
			System.out.println("3 - Menu Filme");
			System.out.println("4 - Menu Serie");
			System.out.println("5 - Menu Documentário");
			System.out.println("6 - Listar conteúdo da plataforma");
			System.out.println("7 - Retirar um conteúdo da conta de um usuário");
			System.out.println("8 - Deslogar");
			opc = Console.readInt("Informe a opção: ");

			switch (opc) {
			case 1:
				do {
					System.out.println("\n");
					System.out.println("*** ADMINS ***");
					System.out.println("1 - Listar administradores");
					System.out.println("2 - Consultar administrador");
					System.out.println("3 - Alterar administrador");
					System.out.println("4 - Excluir administrador");
					System.out.println("5 - Voltar");
					opc = Console.readInt("Opção: ");
					switch (opc) {
					case 1:
						listarAdmin();
						break;
					case 2:
						consultaAdmin();
						break;
					case 3:
						altAdmin();
						break;
					case 4:
						exclusaoAdmin();
						break;
					}
				} while (opc != 5);
				break;
				
			case 2:
				listarUsuario();
				break;
			case 3:
				new CadastroFilme();
				break;
			case 4:
				new CadastroSerie();
				break;
			case 5:
				new CadastroDoc();
				break;
			case 6:
				listarConteudo();
				break;
			case 7:
				removerConteudoAlugado();
				break;
			}
		} while (opc != 8);
	}

	private void listarAdmin() {

		System.out.println("\n\n*** LISTAGEM DE ADMINS ***");

		for (Admin item : AdminPersistencia.getAdmins(adminLogado)) {
			System.out.println("ID: " + item.getId());
			System.out.println("Nome: " + item.getNome());
			System.out.println("Telefone: " + item.getTelefone());
			System.out.println("CPF: " + item.getCpf());
			System.out.println("---------------------------");
		}
	}

	private void consultaAdmin() {
		System.out.println("\n*** CONSULTA DE ADMINISTRADORES ***");
		System.out.println("\n-------------------------");
		System.out.println("\nID: " + adminLogado.getId());
		System.out.println("Nome do ADM: " + adminLogado.getNome());
		System.out.println("Telefone do ADM: " + adminLogado.getTelefone());
		System.out.println("CPF: " + adminLogado.getCpf());
		System.out.println("-------------------------");

	}

	private void altAdmin() {
		System.out.println("\n*** ALTERAÇÃO DE ADMINISTRADORES ***");

		System.out.println("\n\n-------------------------");
		System.out.println("ID: " + adminLogado.getId());
		System.out.println("Nome do ADM: " + adminLogado.getNome());
		System.out.println("Telefone do ADM: " + adminLogado.getTelefone());
		System.out.println("CPF: " + adminLogado.getCpf());
		System.out.println("-------------------------");
		String resp = Console.readString("Quer alterar os dados deste administrador?(S/N) ");
		if (resp.equals("S")) {
			adminLogado.setNome(Console.readString("Informe o novo nome para o administrador: "));
			adminLogado.setTelefone(Console.readInt("Informe o novo telefone para o administrador: "));
			if (AdminPersistencia.alterar(adminLogado) == true) {
				System.out.println("A alteração foi realizada...");
			} else {
				System.out.println("A alteração não pôde ser realizada no momento...");
			}
		}

	}
	
	private void listarUsuario() {
	    System.out.println("\n\n*** LISTAGEM DE USUÁRIOS ***");

	    Usuario usuario = new Usuario(); 
	    usuario.setNome(""); 

	    List<Usuario> usuarios = UsuarioPersistencia.getAdmins(usuario);

	    for (Usuario item : usuarios) {
	        System.out.println("ID: " + item.getId());
	        System.out.println("Nome: " + item.getNome());
	        System.out.println("Telefone: " + item.getTelefone());
	        System.out.println("Email: " + item.getEmail());
	        System.out.println("CPF: " + item.getCpf());
	        System.out.println("---------------------------");
	    }
	}

	private void exclusaoAdmin() {

		System.out.println("\n*** EXCLUSÃO DE CONTA ADM ***");
		System.out.println("\n\n-------------------------");
		System.out.println("ID: " + adminLogado.getId());
		System.out.println("Nome do ADM: " + adminLogado.getNome());
		System.out.println("Telefone do ADM: " + adminLogado.getTelefone());
		System.out.println("CPF: " + adminLogado.getCpf());
		System.out.println("-------------------------");
		String resp = Console.readString("Quer excluir o administrador?(S/N) ");
		if (resp.equals("S")) {
			if (AdminPersistencia.excluir(adminLogado) == true) {
				System.out.println("A exclusão foi realizada...");
			} else {
				System.out.println("A exclusão não pôde ser realizada no momento...");
			}
		}

	}
	
	private void removerConteudoAlugado() {
	    int idUsuario = Console.readInt("Informe o ID do usuário: ");
	    Usuario usuarioTemp = new Usuario();
	    usuarioTemp.setId(idUsuario);

	    // Procurar o usuário por ID
	    Usuario usuario = UsuarioPersistencia.procurarPorId(usuarioTemp);

	    // Verificar se o usuário foi encontrado
	    if (usuario == null) {
	        System.out.println("Usuário não encontrado.");
	        return;
	    }

	    // Exibir informações do usuário
	    System.out.println("Usuário encontrado:");
	    System.out.println("ID: " + usuario.getId());
	    System.out.println("Nome: " + usuario.getNome());

	    // Confirmar remoção com o usuário
	    String resposta = Console.readString("Deseja realmente remover o conteúdo alugado? (S/N): ");
	    if (!resposta.equalsIgnoreCase("S")) {
	        System.out.println("Remoção cancelada.");
	        return;
	    }

	    // Buscar as compras do usuário
	    List<Compra> compras = CompraPersistencia.buscarComprasPorUsuario(usuario);

	    // Obter a data atual
	    LocalDate dataAtual = LocalDate.now();

	    // Iterar sobre as compras
	    for (Compra compra : compras) {
	        // Verificar se a data de validade da compra é anterior à data atual
	        if (compra.getDataValidade().isBefore(dataAtual)) {
	            // Remover o conteúdo alugado da conta do usuário
	            usuario.removerConteudo(compra.getConteudo());

	            // Remover a compra
	            CompraPersistencia.remover(compra);
	        }
	    }
	}

	private static void listarConteudo() {
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

	}
}
