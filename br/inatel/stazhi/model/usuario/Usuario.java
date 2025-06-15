package br.inatel.stazhi.model.usuario;

public abstract class Usuario{
    protected String nome;
    protected String senha;
    protected String email;
    protected int id;

    public Usuario(int id, String nome, String senha, String email){
        this.nome = nome;
        this.senha = senha;
        this.id = id;
        this.email = email;
    }
    
    public abstract void showMenu();
}
