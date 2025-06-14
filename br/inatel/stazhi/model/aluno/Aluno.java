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
    public void login(String email, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public void cadastro(String email, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastro'");
    }

    @Override
    public void showMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showMenu'");
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

}
