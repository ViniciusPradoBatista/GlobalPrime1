package br.edu.up.front;

import br.edu.up.entidades.Series;
import br.edu.up.persistencia.SeriePersistencia;

public class CadastroSerie {
	public CadastroSerie() {
		int opc;

		do {
			System.out.println("\n*** MENU DE SÉRIES ***");
			System.out.println("1 - Adicionar série");
			System.out.println("2 - Listar séries");
			System.out.println("3 - Alterar série");
			System.out.println("4 - Excluir série");
			System.out.println("5 - Voltar");
			opc = Console.readInt("Opção: ");
			switch (opc) {

			case 1:

				novaSerie();
				break;

			case 2:

				listarSeries();
				break;

			case 3:

				alterarSerie();
				break;
				
			case 4:
				
				exclusaoSerie();
				break;

			}
		} while (opc != 5);
	}
		
	/*-------------------------------------------------------------------------*/
	
	private static void novaSerie() {
		
		System.out.println("\n*** CADASTRO DE NOVA SÉRIE ***");
		Series objSerie = new Series();
		objSerie.setNome(Console.readString("\nInforme o nome da série: "));
		if (SeriePersistencia.procurarPorNome(objSerie) == null) {
			objSerie.setDuracao(Console.readString("Informe a duração da série: "));
			objSerie.setAnoEstreia(Console.readInt("Informe o ano de estreia da série: "));
			objSerie.setCategoria(Console.readString("Informe a categoria da série: "));
			objSerie.setClassificacao(Console.readString("Informe a classificação da série: "));
			objSerie.setCriador(Console.readString("Informe o nome do criador da série: "));
			objSerie.setPreco(Console.readDouble("Informe o preço para compra/aluguel da série: "));
			if (SeriePersistencia.incluir(objSerie) == true) {
				System.out.println("\nInclusão bem sucessida...");
			} else {
				System.out.println("\nA inclusão não foi realizada...");
			}
		} else {
			System.out.println("\nSérie já cadastrada...");
		}
	}
	
	private static void listarSeries() {
		System.out.println("\n*** LISTAGEM DE SÉRIES ***");
		Series objSerie = new Series();
		objSerie.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		for (Series item : SeriePersistencia.getSeries(objSerie)) {
			System.out.println("\nID: " + item.getId());
			System.out.println("Nome: " + item.getNome());
			System.out.println("Duração: " + item.getDuracao());
			System.out.println("Ano de estreia: " + item.getAnoEstreia());
			System.out.println("Categoria: " + item.getCategoria());
			System.out.println("Classificação: " + item.getClassificacao());
			System.out.println("Criador: " + item.getCriador());
			System.out.println("Preço: " + item.getPreco());
			System.out.println("---------------------------");
		}

	}
	
	private static void alterarSerie() {
		System.out.println("\n*** ALTERAÇÃO DE SÉRIES ***");
		Series objSerie = new Series();
		objSerie.setNome(Console.readString("Informe o nome da série a ser consultado: "));
		objSerie = SeriePersistencia.procurarPorNome(objSerie);
		if (objSerie != null) {
			System.out.println("\n\n-------------------------");
			System.out.println("ID: " + objSerie.getId());
			System.out.println("Nome do filme: " + objSerie.getNome());
			System.out.println("Duração: " + objSerie.getDuracao());
			System.out.println("Ano de estreia: " + objSerie.getAnoEstreia());
			System.out.println("Categoria: " + objSerie.getCategoria());
			System.out.println("Classificação: " + objSerie.getClassificacao());
			System.out.println("Criador: " + objSerie.getCriador());
			System.out.println("Preço: " + objSerie.getPreco());
			System.out.println("-------------------------");
			String resp = Console.readString("Quer alterar os dados desta série?(S/N) ");
			if (resp.equals("S")) {
				objSerie.setNome(Console.readString("Informe o novo nome para a série: "));
				objSerie.setDuracao(Console.readString("Informe a nova duração: "));
				objSerie.setAnoEstreia(Console.readInt("Informe o ano de estreia: "));
				objSerie.setCategoria(Console.readString("Informe a categoria: "));
				objSerie.setClassificacao(Console.readString("Informe a classificação: "));
				objSerie.setCriador(Console.readString("Informe o criador: "));
				objSerie.setPreco(Console.readDouble("Informe o preço "));
				if (SeriePersistencia.alterar(objSerie) == true) {
					System.out.println("A alteração foi realizada...");
				} else {
					System.out.println("A alteração não pôde ser realizada no momento...");
				}
			}
		} else {
			System.out.println("\n\nSérie não encontrado...");
		}

	}
	
	private static void exclusaoSerie() {

		System.out.println("\n*** EXCLUSÃO DE SÉRIE ***");
		Series objSerie = new Series();
		objSerie.setNome(Console.readString("Informe o nome do filme a ser consultado: "));
		objSerie = SeriePersistencia.procurarPorNome(objSerie);
		if (objSerie != null) {
			System.out.println("\n\n-------------------------");
			System.out.println("ID: " + objSerie.getId());
			System.out.println("Nome do filme: " + objSerie.getNome());
			System.out.println("Duração: " + objSerie.getDuracao());
			System.out.println("Ano de estreia: " + objSerie.getAnoEstreia());
			System.out.println("Categoria: " + objSerie.getCategoria());
			System.out.println("Classificação: " + objSerie.getClassificacao());
			System.out.println("Criador: " + objSerie.getCriador());
			System.out.println("Preço: " + objSerie.getPreco());
			String resp = Console.readString("Quer excluir a série? ");
			if (resp.equals("S")) {
				if (SeriePersistencia.excluir(objSerie) == true) {
					System.out.println("A exclusão foi realizada...");
				} else {
					System.out.println("A exclusão não pôde ser realizada no momento...");
				}
			}
		} else {
			System.out.println("\nSérie não encontrado...");

		}
	}

}
