package br.inatel.stazhi.casodeuso.aluno;

import br.inatel.stazhi.execoes.UsuarioJaExisteException.UsuarioJaExisteException;
import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;
import br.inatel.stazhi.util.geradorDeId.GeradorDeId;

public class CriarAluno {
    public void executar(String nome, String senha, int idade, String formacao, String email) throws UsuarioJaExisteException {
        var repo = new AlunoRepositorio();

        if (repo.existePorEmail(email)) {
            throw new UsuarioJaExisteException("Já existe um aluno com esse email.");
        }

        int id = GeradorDeId.gerarId();

        Aluno novoAluno = new Aluno(id, nome, senha, idade, formacao, email);
        repo.criar(novoAluno);
    }
}