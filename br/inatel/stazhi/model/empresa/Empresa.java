package br.inatel.stazhi.model.empresa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import br.inatel.stazhi.Enum.modalidade.Modalidade;
import br.inatel.stazhi.casodeuso.empresa.CriarVaga;
import br.inatel.stazhi.cli.VagasCLI;
import br.inatel.stazhi.model.usuario.Usuario;

public class Empresa extends Usuario {
    private String CNPJ;
    private String setor;

    public Empresa(int id, String nome, String senha, String email, String CNPJ, String setor) {
        super(id, nome, senha, email);
        this.CNPJ = CNPJ;
        this.setor = setor;
    }

    @Override
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;
        while (escolha!=3) {
            System.out.println("=== Menu da Empresa ===");
            System.out.println("1. Criar Vaga");
            System.out.println("2. Ver suas Vagas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");
            escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1:{
                    VagasCLI.Criar(id);
                    break;
                }
                case 2:
                    VagasCLI.Listar(id);
                    break;
                case 3:
                    System.out.println("saindo");
                    break;
                default:
                    System.out.println("Insira um dos valores especificados");
                    break;
            }
        }
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

    public String getSetor(){
        return this.setor;
    }

    public String getCNPJ(){
        return this.CNPJ;
    }
}
