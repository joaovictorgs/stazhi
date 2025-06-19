package br.inatel.stazhi.casodeuso.supervisor;

import br.inatel.stazhi.execoes.UsuarioJaExisteException.UsuarioJaExisteException;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;
import br.inatel.stazhi.repositorio.supervisorRepositorio.SupervisorRepositorio;

public class SupervisionarAluno {
     public void executar(int alunoId, int supervisorId ) throws UsuarioJaExisteException {
        SupervisorRepositorio repoSupervisor = new SupervisorRepositorio();
        AlunoRepositorio repoAluno = new AlunoRepositorio();
        
        repoAluno.atualizarSupervisorId(alunoId, supervisorId);
     } 
}
