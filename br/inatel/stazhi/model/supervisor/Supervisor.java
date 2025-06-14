package br.inatel.stazhi.model.supervisor;

import br.inatel.stazhi.model.usuario.Usuario;

public class Supervisor extends Usuario {
    private int idade;

    public Supervisor(int id, String nome, String senha, String email, int idade) {
        super(id, nome, senha, email);
        this.idade = idade;
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

    public int getId(){
        return this.id;
    }

    public int getIdade(){
        return this.idade;
    }
}
