package br.inatel.stazhi.model.usuario;

import br.inatel.stazhi.interfaces.autenticavel.Autenticavel;

public abstract class Usuario implements Autenticavel{
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
    
    public void showMenu(){}
}
