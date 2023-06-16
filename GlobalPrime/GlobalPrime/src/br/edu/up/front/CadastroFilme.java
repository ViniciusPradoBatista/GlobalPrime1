package br.edu.up.front;

import br.edu.up.entidades.Filmes;
import br.edu.up.persistencia.FilmePersistencia;

public class CadastroFilme {
	public CadastroFilme() {
		int opc;

		do {
			System.out.println("\n*** MENU DE FILMES ***");
			System.out.println("1 - Adicionar filme");
			System.out.println("2 - Listar filmes");
			System.out.println("3 - Alterar filme");
			System.out.println("4 - Excluir filme");
			System.out.println("5 - Voltar");
			opc = Console.readInt("Opção: ");
			switch (opc) {

			case 1:

				novoFilme();
				break;

			case 2:

				listarFilmes();
				break;

			case 3:

				alterarFilme();
				break;
				
			case 4:
				
				exclusaoFilme();
				break;

			}
		} while (opc != 5);
	}

	/*-----------------------------------------------------------*/

	private static void novoFilme() {

		System.out.println("\n*** CADASTRO DE NOVO FILME ***");
		Filmes objFilme = new Filmes();
		objFilme.setNome(Console.readString("\nInforme o nome do filme: "));
		if (FilmePersistencia.procurarPorNome(objFilme) == null) {
			objFilme.setDuracao(Console.readString("Informe a duração do filme: "));
			objFilme.setAnoEstreia(Console.readInt("Informe o ano de estreia do filme: "));
			objFilme.setCategoria(Console.readString("Informe a categoria do filme: "));
			objFilme.setClassificacao(Console.readString("Informe a classificação do filme: "));
			objFilme.setDiretor(Console.readString("Informe qual o diretor do filme: "));
			objFilme.setPreco(Console.readDouble("Informe o preço para compra/aluguel do filme: "));
			if (FilmePersistencia.incluir(objFilme) == true) {
				System.out.println("\nInclusão bem sucessida...");
			} else {
				System.out.println("\nA inclusão não foi realizada...");
			}
		} else {
			System.out.println("\nFilme já cadastrado...");
		}

	}

	private static void listarFilmes() {
		System.out.println("\n\n*** LISTAGEM DE FILMES ***");
		Filmes objFilme = new Filmes();
		objFilme.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		for (Filmes item : FilmePersistencia.getFilmes(objFilme)) {
			System.out.println("\nID: " + item.getId());
			System.out.println("Nome: " + item.getNome());
			System.out.println("Duração: " + item.getDuracao());
			System.out.println("Ano de estreia: " + item.getAnoEstreia());
			System.out.println("Categoria: " + item.getCategoria());
			System.out.println("Classificação: " + item.getClassificacao());
			System.out.println("Diretor: " + item.getDiretor());
			System.out.println("Preço: " + item.getPreco());
			System.out.println("---------------------------");
		}

	}

	private static void alterarFilme() {
		System.out.println("\n*** ALTERAÇÃO DE FILMES ***");
		Filmes objFilme = new Filmes();
		objFilme.setNome(Console.readString("Informe o nome do filme a ser consultado: "));
		objFilme = FilmePersistencia.procurarPorNome(objFilme);
		if (objFilme != null) {
			System.out.println("\n\n-------------------------");
			System.out.println("ID: " + objFilme.getId());
			System.out.println("Nome do filme: " + objFilme.getNome());
			System.out.println("Duração: " + objFilme.getDuracao());
			System.out.println("Ano de estreia: " + objFilme.getAnoEstreia());
			System.out.println("Categoria: " + objFilme.getCategoria());
			System.out.println("Classificação: " + objFilme.getClassificacao());
			System.out.println("Diretor: " + objFilme.getDiretor());
			System.out.println("Preço: " + objFilme.getPreco());
			System.out.println("-------------------------");
			String resp = Console.readString("Quer alterar os dados deste filme?(S/N) ");
			if (resp.equals("S")) {
				objFilme.setNome(Console.readString("Informe o novo nome para o filme: "));
				objFilme.setDuracao(Console.readString("Informe a nova duração: "));
				objFilme.setAnoEstreia(Console.readInt("Informe o ano de estreia: "));
				objFilme.setCategoria(Console.readString("Informe a categoria: "));
				objFilme.setClassificacao(Console.readString("Informe a classificação: "));
				objFilme.setDiretor(Console.readString("Informe o diretor: "));
				objFilme.setPreco(Console.readDouble("Informe o preço: "));
				if (FilmePersistencia.alterar(objFilme) == true) {
					System.out.println("A alteração foi realizada...");
				} else {
					System.out.println("A alteração não pôde ser realizada no momento...");
				}
			}
		} else {
			System.out.println("\n\nFilme não encontrado...");
		}

	}

	private static void exclusaoFilme() {

		System.out.println("\n*** EXCLUSÃO DE FILME ***");
		Filmes objFilme = new Filmes();
		objFilme.setNome(Console.readString("Informe o nome do filme a ser consultado: "));
		objFilme = FilmePersistencia.procurarPorNome(objFilme);
		if (objFilme != null) {
			System.out.println("\n\n-------------------------");
			System.out.println("ID: " + objFilme.getId());
			System.out.println("Nome do filme: " + objFilme.getNome());
			System.out.println("Duração: " + objFilme.getDuracao());
			System.out.println("Ano de estreia: " + objFilme.getAnoEstreia());
			System.out.println("Categoria: " + objFilme.getCategoria());
			System.out.println("Classificação: " + objFilme.getClassificacao());
			System.out.println("Diretor: " + objFilme.getDiretor());
			System.out.println("Preço: " + objFilme.getPreco());
			String resp = Console.readString("Quer excluir o filme? ");
			if (resp.equals("S")) {
				if (FilmePersistencia.excluir(objFilme) == true) {
					System.out.println("A exclusão foi realizada...");
				} else {
					System.out.println("A exclusão não pôde ser realizada no momento...");
				}
			}
		} else {
			System.out.println("\n\nFilme não encontrado...");

		}
	}

}
