package br.inatel.stazhi.casodeuso.supervisor;

import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;

public class SupervisionarAluno {
     public void executar(int alunoId, int supervisorId ){
        AlunoRepositorio repoAluno = new AlunoRepositorio();
        
        repoAluno.atualizarSupervisorId(alunoId, supervisorId);
     } 
}
