package br.edu.up.front;

import br.edu.up.entidades.Usuario;
import br.edu.up.negocio.CadastroNegocio;
import br.edu.up.persistencia.UsuarioPersistencia;

public class CadastroUsuario {
	public CadastroUsuario() {
		int opc;

		do {
			System.out.println("\n*** CADASTRO DE CLIENTE ***");
			System.out.println("\nBem-vindo(a) a criação de conta de clientes para a GlobalPrime!");
			System.out.println("\n1 - Criar conta");
			System.out.println("2 - Voltar");
			opc = Console.readInt("Informe a opção: ");

			switch (opc) {

			case 1:

				novoCliente();
				break;

			}

		} while (opc != 2);

	}
	/*-----------------------------------------------------------*/
	
	private static void novoCliente() {
		System.out.println("\n*** CADASTRO DE NOVO CLIENTE ***");
		Usuario objCliente = new Usuario();
		objCliente.setCpf(Console.readString("\nInforme o seu CPF: "));
		if (CadastroNegocio.isCPF(objCliente.getCpf())) {
			if (UsuarioPersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setNome(Console.readString("Informe o seu nome: "));
				do {
					String senha = Console.readString("Informe uma senha: ");
					objCliente.setSenha(senha);
				} while (objCliente.getSenha() == null);
				objCliente.setTelefone(Console.readInt("Informe o seu telefone: "));
				objCliente.setEmail(Console.readString("Informe seu email: "));
				objCliente.setConta(0);
				if (UsuarioPersistencia.incluir(objCliente) == true) {
					System.out.println("\nInclusão bem sucessida...");
				} else {
					System.out.println("\nA inclusão não foi realizada...");
				}
			} else {
				System.out.println("\nCliente já cadastrado...");
			}
		} else {
			System.out.println("\nCPF inválido...");
		}
	}

}
