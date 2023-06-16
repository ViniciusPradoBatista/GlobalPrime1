package br.edu.up.front;

import br.edu.up.entidades.Documentarios;
import br.edu.up.persistencia.DocumentarioPersistencia;

public class CadastroDoc {
	public CadastroDoc() {
		int opc;

		do {
			System.out.println("\n*** MENU DE DOCUMENTÁRIOS ***");
			System.out.println("1 - Adicionar documentário");
			System.out.println("2 - Listar documentários");
			System.out.println("3 - Alterar documentário");
			System.out.println("4 - Excluir documentário");
			System.out.println("5 - Voltar");
			opc = Console.readInt("Opção: ");
			switch (opc) {

			case 1:

				novoDoc();
				break;

			case 2:

				listarDocs();
				break;

			case 3:

				alterarDoc();
				break;
				
			case 4:
				
				exclusaoDoc();
				break;

			}
		} while (opc != 5);
	}

	/*-----------------------------------------------------------*/

	private static void novoDoc() {

		System.out.println("\n*** CADASTRO DE NOVO DOCUMENTÁRIO ***");
		Documentarios objDoc = new Documentarios();
		objDoc.setNome(Console.readString("\nInforme o nome do documentário: "));
		if (DocumentarioPersistencia.procurarPorNome(objDoc) == null) {
			objDoc.setDuracao(Console.readString("Informe a duração do documentário: "));
			objDoc.setAnoEstreia(Console.readInt("Informe o ano de estreia do documentário: "));
			objDoc.setCategoria(Console.readString("Informe a categoria do documentário: "));
			objDoc.setClassificacao(Console.readString("Informe a classificação do documentário: "));
			objDoc.setDiretor(Console.readString("Informe qual o diretor do documentário: "));
			objDoc.setPreco(Console.readDouble("Informe o preço para compra/aluguel do documentário: "));
			if (DocumentarioPersistencia.incluir(objDoc) == true) {
				System.out.println("\nInclusão bem sucessida...");
			} else {
				System.out.println("\nA inclusão não foi realizada...");
			}
		} else {
			System.out.println("\nDocumentário já cadastrado...");
		}

	}

	private static void listarDocs() {
		System.out.println("\n\n*** LISTAGEM DE DOCUMÉNTARIOS ***");
		Documentarios objDoc = new Documentarios();
		objDoc.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		for (Documentarios item : DocumentarioPersistencia.getDoc(objDoc)) {
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

	private static void alterarDoc() {
		System.out.println("\n*** ALTERAÇÃO DE DOCUMÉNTARIOS ***");
		Documentarios objDoc = new Documentarios();
		objDoc.setNome(Console.readString("Informe o nome do filme a ser consultado: "));
		objDoc = DocumentarioPersistencia.procurarPorNome(objDoc);
		if (objDoc != null) {
			System.out.println("\n\n-------------------------");
			System.out.println("ID: " + objDoc.getId());
			System.out.println("Nome do filme: " + objDoc.getNome());
			System.out.println("Duração: " + objDoc.getDuracao());
			System.out.println("Ano de estreia: " + objDoc.getAnoEstreia());
			System.out.println("Categoria: " + objDoc.getCategoria());
			System.out.println("Classificação: " + objDoc.getClassificacao());
			System.out.println("Diretor: " + objDoc.getDiretor());
			System.out.println("Preço: " + objDoc.getPreco());
			System.out.println("-------------------------");
			String resp = Console.readString("Quer alterar os dados deste filme?(S/N) ");
			if (resp.equals("S")) {
				objDoc.setNome(Console.readString("Informe o novo nome para o documentário: "));
				objDoc.setDuracao(Console.readString("Informe a nova duração: "));
				objDoc.setAnoEstreia(Console.readInt("Informe o ano de estreia: "));
				objDoc.setCategoria(Console.readString("Informe a categoria: "));
				objDoc.setClassificacao(Console.readString("Informe a classificação: "));
				objDoc.setDiretor(Console.readString("Informe o diretor: "));
				objDoc.setPreco(Console.readDouble("Informe o preço: "));
				if (DocumentarioPersistencia.alterar(objDoc) == true) {
					System.out.println("A alteração foi realizada...");
				} else {
					System.out.println("A alteração não pôde ser realizada no momento...");
				}
			}
		} else {
			System.out.println("\n\nDocumentário não encontrado...");
		}

	}

	private static void exclusaoDoc() {

		System.out.println("\n*** EXCLUSÃO DE DOCUMÉNTARIOS ***");
		Documentarios objDoc = new Documentarios();
		objDoc.setNome(Console.readString("Informe o nome do documentário a ser consultado: "));
		objDoc = DocumentarioPersistencia.procurarPorNome(objDoc);
		if (objDoc != null) {
			System.out.println("\n\n-------------------------");
			System.out.println("ID: " + objDoc.getId());
			System.out.println("Nome do documentário: " + objDoc.getNome());
			System.out.println("Duração: " + objDoc.getDuracao());
			System.out.println("Ano de estreia: " + objDoc.getAnoEstreia());
			System.out.println("Categoria: " + objDoc.getCategoria());
			System.out.println("Classificação: " + objDoc.getClassificacao());
			System.out.println("Diretor: " + objDoc.getDiretor());
			System.out.println("Preço: " + objDoc.getPreco());
			System.out.println("\n-------------------------");
			String resp = Console.readString("Quer excluir o documentário? ");
			if (resp.equals("S")) {
				if (DocumentarioPersistencia.excluir(objDoc) == true) {
					System.out.println("A exclusão foi realizada...");
				} else {
					System.out.println("A exclusão não pôde ser realizada no momento...");
				}
			}
		} else {
			System.out.println("\n\nDocumentário não encontrado...");

		}
	}

}