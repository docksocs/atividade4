package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;

public class Programa {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, SQLException {
		
		try {
        	
            int opcao = 0;
            @SuppressWarnings("resource")
			Scanner console = new Scanner(System.in);
            
            do {
                System.out.print("######## Menu ########"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOpção: ");
                opcao = Integer.parseInt(console.nextLine());
                System.out.println("\n\n\n\n");
                
				if (opcao == 1) {
					Aluno a = new Aluno();
					AlunoJDBC acao = new AlunoJDBC();
					System.out.print("\n*** Cadastrar Aluno ***\n\r");
					System.out.print("Nome: ");
					a.setNome(console.nextLine());
					System.out.print("Sexo: ");
					a.setSexo(console.nextLine());
					System.out.print("Data de nascimento: ");
					a.setDt_nasc(new Date(console.nextLine()));
					acao.salvar(a);
					console.nextLine();
					System.out.println("\n\n\n\n");
					
				} else if (opcao == 2) {
					
					Aluno a1 = new Aluno();
					AlunoJDBC acao1 = new AlunoJDBC();
					System.out.print("\n*** Listar Aluno ***\n\r");
					System.out.print("Nome: " +a1.getNome());
					console.nextLine();
					System.out.print("Sexo: " +a1.getSexo());
					console.nextLine();
					System.out.print("Data de nascimento: " +	a1.getDt_nasc());
					acao1.salvar(a1);
					console.nextLine();
					System.out.println("\n\n\n\n");
					
				} else if (opcao == 3) {
					
					Aluno a3 = new Aluno();
					AlunoJDBC acao3 = new AlunoJDBC();
					System.out.print("\n*** Alterar Aluno ***\n\r");
					System.out.print("Id: ");
					a3.setId(console.nextInt());
					System.out.print("Nome: ");
					a3.setNome(console.nextLine());
					acao3.alterar(a3);
					console.nextLine();
					System.out.println("\n\n\n\n");
					
				} else if (opcao == 4) {
					AlunoJDBC acao4 = new AlunoJDBC();
					System.out.print("\n*** Excluir Aluno ***\n\r");
					System.out.print("Digite o Id do Aluno a ser excluido:");
					acao4.apagar(console.nextInt());
					console.nextLine();
					System.out.println("\n\n\n\n");
				} else {
					System.out.println("Erro");
				}
			} while (opcao != 5);
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	}
		
}

