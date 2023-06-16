package br.edu.up.front;

import br.edu.up.entidades.Admin;
import br.edu.up.negocio.CadastroNegocio;
import br.edu.up.persistencia.AdminPersistencia;

public class CadastroAdmin {
	public CadastroAdmin() {
		int opc;

		do {
			System.out.println("\n*** CADASTRO DE ADMINISTRADOR ***");
			System.out.println("\nBem-vindo(a) a criação de conta de administrador para a GlobalPrime!");
			System.out.println("\n1 - Criar conta");
			System.out.println("2 - Voltar");
			opc = Console.readInt("Informe a opção: ");

			switch (opc) {

			case 1:

				novoAdmin();
				break;

			}

		} while (opc != 2);

	}
	/*-----------------------------------------------------------*/

	private static void novoAdmin() {
		System.out.println("\n*** CADASTRO DE NOVO ADMINISTRADOR ***");
		Admin objAdmin = new Admin();
		objAdmin.setCpf(Console.readString("\nInforme o seu CPF: "));
		if (CadastroNegocio.isCPF(objAdmin.getCpf())) {
			if (AdminPersistencia.procurarPorCPF(objAdmin) == null) {
				objAdmin.setNome(Console.readString("Informe o seu nome: "));
				do {
					String senha = Console.readString("Informe uma senha: ");
					objAdmin.setSenha(senha);
				} while (objAdmin.getSenha() == null);
				objAdmin.setTelefone(Console.readInt("Informe o seu telefone: "));
				if (AdminPersistencia.incluir(objAdmin) == true) {
					System.out.println("\nInclusão bem sucessida...");
				} else {
					System.out.println("\nA inclusão não foi realizada...");
				}
			} else {
				System.out.println("\nAdmin já cadastrado...");
			}
		} else {
			System.out.println("\nCPF inválido...");
		}
	}

}
