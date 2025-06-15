package br.inatel.stazhi.model.aluno;

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
        System.out.println("=== Menu do Aluno ===");
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
