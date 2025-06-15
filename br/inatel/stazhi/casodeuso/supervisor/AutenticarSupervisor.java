package br.inatel.stazhi.casodeuso.supervisor;

import br.inatel.stazhi.execoes.dadosInvalidosException.DadosInvalidosException;
import br.inatel.stazhi.model.supervisor.Supervisor;
import br.inatel.stazhi.repositorio.supervisorRepositorio.SupervisorRepositorio;

public class AutenticarSupervisor {

    private final SupervisorRepositorio supervisorRepositorio;

    public AutenticarSupervisor() {
        this.supervisorRepositorio = new SupervisorRepositorio();
    }

    public Supervisor executar(String email, String senha) throws DadosInvalidosException {
        Supervisor supervisor = supervisorRepositorio.buscarPorEmail(email);
        
        if (supervisor == null || !supervisor.getSenha().equals(senha)) {
            throw new DadosInvalidosException("Senha inválida ou usuário não encontrado.");
        }
        return supervisor;
    }
}
