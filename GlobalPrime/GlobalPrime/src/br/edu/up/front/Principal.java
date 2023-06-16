package br.edu.up.front;
public class Principal {
	public static void main(String[] args) throws InterruptedException {
		int opc;
		boolean plataformaInstalada = false;
		do {
			System.out.println("\n*** MENU PRINCIPAL ***");
			System.out.println("1 - Cadastro Admin");
			System.out.println("2 - Cadastro Cliente");
			System.out.println("3 - Baixar GlobalPrime");
			System.out.println("4 - Login GlobalPrime");
			System.out.println("5 - Sair");
			opc = Console.readInt("Informe a opção: ");
			
			switch(opc) {
			
			case 1:
				new CadastroAdmin();
				break;
				
			case 2:
				new CadastroUsuario();
				break;
				
			case 3:
				
				if (plataformaInstalada) {
			        System.out.println("\nA plataforma já foi instalada!");
			    } else {
			        System.out.println("\n***Iniciando download da plataforma!***\n");
			        for (int progresso = 0; progresso <= 100; progresso += 10) {
			            System.out.println("Progresso: " + progresso + "%");
			            Thread.sleep(1000);

			            if (progresso == 100) {
			                plataformaInstalada = true;
			            }
			        }

			        System.out.println("\nDownload concluído!");
			    }
			    break;
			    
			case 4:
				plataformaInstalada = true;
				if(plataformaInstalada) {
					
					new LogarPlataforma();
					
				}else {
					System.out.println("\nA plataforma ainda não foi instalada!");
				}

				break;
				
			case 5:
				
				System.out.println("O programa será finalizado...");
				
			}

		}while(opc != 5);
	
	}
	
}
