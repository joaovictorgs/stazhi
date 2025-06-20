package br.inatel.stazhi.model.aluno;

import java.util.Scanner;

import br.inatel.stazhi.cli.GerenciarmentoEstagiariosCLI;
import br.inatel.stazhi.cli.SupervisionamentoCLI;
import br.inatel.stazhi.cli.VagasCLI;
import br.inatel.stazhi.execoes.vagaJaExisteException.VagaJaExisteException;
import br.inatel.stazhi.model.usuario.Usuario;

public class Aluno extends Usuario {
    private int idade;
    private String formacao;

    public Aluno(int id, String nome, String senha, int idade, String formacao, String email) {
        super(id, nome, senha, email);
        this.idade = idade;
        this.formacao = formacao;
    }

    @Override
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;
        while (escolha!=5) {
            System.out.println("=== Menu do Aluno ===");
            System.out.println("1. Visualizar Vagas");
            System.out.println("2. Se Inscrever na Vaga");
            System.out.println("3. Visualizar Supervisor");
            System.out.println("4. Visualizar Empresa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opcao: ");
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    VagasCLI.Listar();
                    break;
                case 2:
                    try {
                        VagasCLI.inscreverNaVaga(this.id);
                    } catch (VagaJaExisteException e) {
                        
                        System.out.println(e.getMessage()); 
                    }
                    break;
                case 3:
                    SupervisionamentoCLI.BuscarSupervisorPorAlunoId(this.id);
                    break;
                case 4:
                    GerenciarmentoEstagiariosCLI.VerEmpresa(this.id);
                    break;
                case 5:
                    System.out.println("saindo");
                    break;
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

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getFormacao() {
        return this.formacao;
    }

    public int getId() {
        return this.id;
    }

}
