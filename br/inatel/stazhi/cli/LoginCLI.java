package br.inatel.stazhi.cli;

import java.util.Scanner;

import br.inatel.stazhi.casodeuso.aluno.AutenticarAluno;
import br.inatel.stazhi.casodeuso.aluno.CriarAluno;
import br.inatel.stazhi.casodeuso.supervisor.AutenticarSupervisor;
import br.inatel.stazhi.casodeuso.supervisor.CriarSupervisor;
import br.inatel.stazhi.casodeuso.empresa.AutenticarEmpresa;
import br.inatel.stazhi.casodeuso.empresa.CriarEmpresa;

import br.inatel.stazhi.execoes.UsuarioJaExisteException.UsuarioJaExisteException;
import br.inatel.stazhi.execoes.dadosInvalidosException.DadosInvalidosException;
import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.model.empresa.Empresa;
import br.inatel.stazhi.model.supervisor.Supervisor;
import br.inatel.stazhi.model.usuario.Usuario;

public class LoginCLI {

    public static Usuario iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bem-vindo ao STAZHI ===");
        System.out.println("1. Fazer Login");
        System.out.println("2. Criar nova conta");
        System.out.print("Escolha uma opcao: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                return login(scanner);
            case 2:
                return criarConta(scanner);
            default:
                System.out.println("Opcao invalida.");
                return null;
        }
    }

    private static Usuario login(Scanner scanner) {
        System.out.println("=== Login ===");
        int tipo = escolherTipoUsuario(scanner);

        scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        try {
            switch (tipo) {
                case 1:
                    return new AutenticarAluno().executar(email, senha);
                case 2:
                    return new AutenticarSupervisor().executar(email, senha);
                case 3:
                    return new AutenticarEmpresa().executar(email, senha);
                default:
                    System.out.println("Opcao invalida.");
                    return null;
            }
        } catch (DadosInvalidosException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Usuario criarConta(Scanner scanner) {
        System.out.println("=== Criar Nova Conta ===");
        int tipo = escolherTipoUsuario(scanner);

        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        String formacao = null;
        String cnpj = null;
        String setor = null;

        if (tipo == 1) {
            System.out.print("Formacao: ");
            formacao = scanner.nextLine();
        } else if (tipo == 3) {
            System.out.print("CNPJ: ");
            cnpj = scanner.nextLine();
            System.out.print("Setor: ");
            setor = scanner.nextLine();
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            switch (tipo) {
                case 1 -> {
                    new CriarAluno().executar(nome, senha, idade, formacao, email);
                    Aluno aluno = new AutenticarAluno().executar(email, senha);
                    
                    System.out.println(aluno.getNome() + ", bem-vindo ao STAZHI!");
                    return aluno;
                }
                case 2 -> {
                    new CriarSupervisor().executar(nome, senha, email, idade);
                    Supervisor supervisor = new AutenticarSupervisor().executar(email, senha);
                    System.out.println(supervisor.getNome() + ", bem-vindo ao STAZHI!");
                    return supervisor;
                }
                case 3 -> {
                    new CriarEmpresa().executar(nome, cnpj, senha, email, setor);
                    Empresa empresa = new AutenticarEmpresa().executar(email, senha);
                    System.out.println(empresa.getNome() + ", bem-vindo ao STAZHI!");
                    return empresa;
                }
                default -> {
                    System.out.println("Opcao invalida.");
                    return null;
                }
            }
        } catch (UsuarioJaExisteException e) {
            System.out.println(e.getMessage());
        } catch (DadosInvalidosException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static int escolherTipoUsuario(Scanner scanner) {
        System.out.println("Escolha o tipo de usuario:");
        System.out.println("1. Aluno");
        System.out.println("2. Supervisor");
        System.out.println("3. Empresa");
        System.out.print("Opcao: ");
        return scanner.nextInt();
    }
}
