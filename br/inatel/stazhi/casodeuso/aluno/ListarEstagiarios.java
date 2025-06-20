package br.inatel.stazhi.casodeuso.aluno;

import java.util.List;

import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;

public class ListarEstagiarios {
    private AlunoRepositorio repositorio;

    public ListarEstagiarios() {
       repositorio = new AlunoRepositorio();
    }

    public List<Aluno> executar(int idEmpresa) {
        return repositorio.listarEstagiariosPorEmpresa(idEmpresa); 
    }
}
