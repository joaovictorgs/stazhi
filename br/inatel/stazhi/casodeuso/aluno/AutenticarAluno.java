package br.inatel.stazhi.casodeuso.aluno;

import br.inatel.stazhi.execoes.dadosInvalidosException.DadosInvalidosException;
import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;

public class AutenticarAluno {

    private final AlunoRepositorio alunoRepositorio;

    public AutenticarAluno() {
        this.alunoRepositorio = new AlunoRepositorio();
    }

    public Aluno executar(String email, String senha) throws DadosInvalidosException {
        Aluno aluno = alunoRepositorio.buscarPorEmail(email);

        if(aluno == null || !aluno.getSenha().equals(senha)) {
            throw new DadosInvalidosException( "Senha invalida ou usuario nao encontrado.");
        }

        return aluno;
    }
}