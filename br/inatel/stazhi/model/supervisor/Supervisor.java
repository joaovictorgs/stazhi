package br.inatel.stazhi.model.supervisor;

import java.util.Scanner;

import br.inatel.stazhi.cli.SupervisionamentoCLI;
import br.inatel.stazhi.model.usuario.Usuario;

public class Supervisor extends Usuario {
    private int idade;

    public Supervisor(int id, String nome, String senha, String email, int idade) {
        super(id, nome, senha, email);
        this.idade = idade;
    }

    @Override
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;
        while (escolha!=4) {
        System.out.println("=== Menu do Supervisor ===");
        System.out.println("1. Verificar meus alunos");
        System.out.println("2. Adicionar alunos pra supervisionar");
        System.out.println("3. Remover aluno supervisionado");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
        escolha = scanner.nextInt();
            switch (escolha) {
                case 1:{
                    SupervisionamentoCLI.ListarAlunosSupervisionados(id);
                    break;
                }
                case 2:{
                    SupervisionamentoCLI.AdicionarAlunoSupervisionado(id);
                    break;
                }
                case 3:{
                    SupervisionamentoCLI.PararDeSupervisionar(id);
                    break;
                }
                case 4:{
                    System.out.println("saindo");
                    break;
                }
             default:
                    System.out.println("Insira um dos valores especificados");
                    break;
            }
        }
        scanner.close();
    }
    
    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getSenha(){
        return this.senha;
    }

    public int getId(){
        return this.id;
    }

    public int getIdade(){
        return this.idade;
    }
}
