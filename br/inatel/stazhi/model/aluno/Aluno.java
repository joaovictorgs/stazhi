package br.inatel.stazhi.model.aluno;

import br.inatel.stazhi.model.usuario.Usuario;

public class Aluno extends Usuario {

    public Aluno(int id, String nome, String email) {
        super(id, nome, email);
        //TODO Auto-generated constructor stub
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

}
