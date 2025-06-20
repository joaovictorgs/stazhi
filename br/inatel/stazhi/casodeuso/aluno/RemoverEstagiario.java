package br.inatel.stazhi.casodeuso.aluno;

import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;

public class RemoverEstagiario {
    private AlunoRepositorio repositorio;

    public RemoverEstagiario() {
        repositorio = new AlunoRepositorio();
    }

    public void executar(int idEstagiario) {
        repositorio.removerEstagiario(idEstagiario);
    }
}
