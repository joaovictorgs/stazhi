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
        
        int escolha = -1;
        do {
            System.out.println("1. Fazer Login");
            System.out.println("2. Criar nova conta");
            System.out.print("Escolha uma opcao: ");
            
            try {
                escolha = scanner.nextInt();
                scanner.nextLine();
                
                if (escolha < 1 || escolha > 2) {
                    System.out.println("Opção inválida! Digite apenas 1 ou 2. Tente novamente.\n");
                    escolha = -1;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite apenas números (1 ou 2). Tente novamente.\n");
                scanner.nextLine();
                escolha = -1;
            }
        } while (escolha == -1);

        switch (escolha) {
            case 1:
                return login(scanner);
            case 2:
                return criarConta(scanner);
            default:
                return null;
        }
    }

    private static Usuario login(Scanner scanner) {
        System.out.println("\n=== Login ===");
        int tipo = escolherTipoUsuario(scanner);

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email não pode estar vazio. Tente novamente.");
            } else if (!email.contains("@")) {
                System.out.println("Email deve conter '@'. Tente novamente.");
            }
        } while (email.isEmpty() || !email.contains("@"));

        String senha;
        do {
            System.out.print("Senha: ");
            senha = scanner.nextLine().trim();
            if (senha.isEmpty()) {
                System.out.println("Senha não pode estar vazia. Tente novamente.");
            }
        } while (senha.isEmpty());

        try {
            switch (tipo) {
                case 1:
                    return new AutenticarAluno().executar(email, senha);
                case 2:
                    return new AutenticarSupervisor().executar(email, senha);
                case 3:
                    return new AutenticarEmpresa().executar(email, senha);
                default:
                    return null;
            }
        } catch (DadosInvalidosException e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            System.out.println("Tente fazer login novamente ou criar uma nova conta.");
            return null;
        }
    }

    private static Usuario criarConta(Scanner scanner) {
        System.out.println("\n=== Criar Nova Conta ===");
        int tipo = escolherTipoUsuario(scanner);

        String nome;
        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode estar vazio. Tente novamente.");
            } else if (nome.length() < 2) {
                System.out.println("Nome deve ter pelo menos 2 caracteres. Tente novamente.");
            }
        } while (nome.isEmpty() || nome.length() < 2);

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email não pode estar vazio. Tente novamente.");
            } else if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Email deve ter formato válido (exemplo@dominio.com). Tente novamente.");
            }
        } while (email.isEmpty() || !email.contains("@") || !email.contains("."));

        int idade = -1;
        do {
            System.out.print("Idade: ");
            try {
                idade = scanner.nextInt();
                scanner.nextLine();
                
                if (idade < 16 || idade > 120) {
                    System.out.println("Idade deve estar entre 16 e 120 anos. Tente novamente.");
                    idade = -1;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite apenas números para a idade. Tente novamente.");
                scanner.nextLine();
                idade = -1;
            }
        } while (idade == -1);

        String formacao = null;
        String cnpj = null;
        String setor = null;

        if (tipo == 1) {
            do {
                System.out.print("Formação: ");
                formacao = scanner.nextLine().trim();
                if (formacao.isEmpty()) {
                    System.out.println("Formação não pode estar vazia. Tente novamente.");
                }
            } while (formacao.isEmpty());
            
        } else if (tipo == 3) {
            do {
                System.out.print("CNPJ (apenas numeros): ");
                cnpj = scanner.nextLine().trim().replaceAll("[^0-9]", "");
                if (cnpj.isEmpty()) {
                    System.out.println("CNPJ não pode estar vazio. Tente novamente.");
                } else if (cnpj.length() != 14) {
                    System.out.println("CNPJ deve ter 14 dígitos. Tente novamente.");
                }
            } while (cnpj.isEmpty() || cnpj.length() != 14);
            
            do {
                System.out.print("Setor: ");
                setor = scanner.nextLine().trim();
                if (setor.isEmpty()) {
                    System.out.println("Setor não pode estar vazio. Tente novamente.");
                }
            } while (setor.isEmpty());
        }

        String senha;
        do {
            System.out.print("Senha (minimo 6 caracteres): ");
            senha = scanner.nextLine().trim();
            if (senha.isEmpty()) {
                System.out.println("Senha não pode estar vazia. Tente novamente.");
            } else if (senha.length() < 6) {
                System.out.println("Senha deve ter pelo menos 6 caracteres. Tente novamente.");
            }
        } while (senha.isEmpty() || senha.length() < 6);

        try {
            switch (tipo) {
                case 1 -> {
                    new CriarAluno().executar(nome, senha, idade, formacao, email);
                    Aluno aluno = new AutenticarAluno().executar(email, senha);
                    System.out.println("\n" + aluno.getNome() + ", bem-vindo ao STAZHI!");
                    return aluno;
                }
                case 2 -> {
                    new CriarSupervisor().executar(nome, senha, email, idade);
                    Supervisor supervisor = new AutenticarSupervisor().executar(email, senha);
                    System.out.println("\n" + supervisor.getNome() + ", bem-vindo ao STAZHI!");
                    return supervisor;
                }
                case 3 -> {
                    new CriarEmpresa().executar(nome, cnpj, senha, email, setor);
                    Empresa empresa = new AutenticarEmpresa().executar(email, senha);
                    System.out.println("\n" + empresa.getNome() + ", bem-vindo ao STAZHI!");
                    return empresa;
                }
                default -> {
                    return null;
                }
            }
        } catch (UsuarioJaExisteException e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
            System.out.println("Tente novamente com um email diferente.");
        } catch (DadosInvalidosException e) {
            System.out.println("Erro nos dados fornecidos: " + e.getMessage());
            System.out.println("Verifique os dados e tente novamente.");
        }
        return null;
    }

    private static int escolherTipoUsuario(Scanner scanner) {
        int tipo = -1;
        do {
            System.out.println("\nEscolha o tipo de usuario:");
            System.out.println("1. Aluno");
            System.out.println("2. Supervisor");
            System.out.println("3. Empresa");
            System.out.print("Opcao: ");
            
            try {
                tipo = scanner.nextInt();
                scanner.nextLine();
                
                if (tipo < 1 || tipo > 3) {
                    System.out.println("Opcao invalida! Digite apenas 1, 2 ou 3. Tente novamente.");
                    tipo = -1;
                }
            } catch (Exception e) {
                System.out.println("Entrada invalida! Digite apenas números (1, 2 ou 3). Tente novamente.");
                scanner.nextLine();
                tipo = -1;
            }
        } while (tipo == -1);
        
        return tipo;
    }
}