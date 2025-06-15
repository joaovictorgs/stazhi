package br.inatel.stazhi.model.supervisor;

import br.inatel.stazhi.model.usuario.Usuario;

public class Supervisor extends Usuario {
    private int idade;

    public Supervisor(int id, String nome, String senha, String email, int idade) {
        super(id, nome, senha, email);
        this.idade = idade;
    }

    @Override
    public void showMenu() {
        System.out.println("=== Menu do Supervisor ===");
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

    public int getIdade(){
        return this.idade;
    }
}
