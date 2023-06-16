package br.edu.up.front;

import br.edu.up.entidades.Admin;
import br.edu.up.entidades.Usuario;
import br.edu.up.persistencia.AdminPersistencia;
import br.edu.up.persistencia.UsuarioPersistencia;

public class LogarPlataforma {
	public LogarPlataforma() {
		int opc;

		do {
			System.out.println("\nBem-vindo(a) a GlobalPrime!");
			System.out.println("\n*** LOGIN PARA... ***");
			System.out.println("\n1 - ADMIN");
			System.out.println("2 - CLIENTE");
			System.out.println("3 - VOLTAR");
			opc = Console.readInt("Informe a opção: ");

			switch (opc) {
			case 1:
				loginAdmin();
				break;
			case 2:
				loginCliente();
				break;
			}
		} while (opc != 3);
	}
	
	private void loginAdmin() {
		Admin objAdmin = new Admin();
		objAdmin.setCpf(Console.readString("Informe seu CPF para logar: "));
		Admin adminEncontrado = AdminPersistencia.procurarPorCPF(objAdmin);
		
		if(adminEncontrado != null) {
			boolean senhaValida = false;
			
			while(!senhaValida) {
				String senha = Console.readString("Informe a senha: ");
				senhaValida = AdminPersistencia.validarSenha(adminEncontrado, senha);
				
				if (senhaValida) {
					System.out.println("\nLogin efetuado com sucesso!");
					
					MenuAdmin menuAdmin = new MenuAdmin(adminEncontrado);
					menuAdmin.exibirMenu();
				}
			}
		}
	}

	private void loginCliente() {
		Usuario objCliente = new Usuario();
		objCliente.setCpf(Console.readString("Informe seu CPF para logar: "));
		Usuario usuarioEncontrado = UsuarioPersistencia.procurarPorCPF(objCliente);

		if (usuarioEncontrado != null) {
			boolean senhaValida = false;

			while (!senhaValida) {
				String senha = Console.readString("Informe a senha: ");
				senhaValida = UsuarioPersistencia.validarSenha(usuarioEncontrado, senha);

				if (senhaValida) {
					System.out.println("\nLogin efetuado com sucesso!");

					MenuCliente menuCliente = new MenuCliente(usuarioEncontrado);
					menuCliente.exibirMenu();
				}
			}
		}
	}
}
