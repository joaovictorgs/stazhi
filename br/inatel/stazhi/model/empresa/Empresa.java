package br.inatel.stazhi.model.empresa;

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
        System.out.println("=== Menu da Empresa ===");
        System.out.println("1. Visualizar Dados");
        System.out.println("2. Editar Dados");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
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
