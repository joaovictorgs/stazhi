package br.inatel.stazhi.casodeuso.supervisor;

import br.inatel.stazhi.execoes.UsuarioJaExisteException.UsuarioJaExisteException;
import br.inatel.stazhi.model.supervisor.Supervisor;
import br.inatel.stazhi.repositorio.supervisorRepositorio.SupervisorRepositorio;
import br.inatel.stazhi.util.geradorDeId.GeradorDeId;

public class CriarSupervisor {
    public void executar(String nome, String senha, String email, int idade) throws UsuarioJaExisteException {
        var repo = new SupervisorRepositorio();

        if (repo.existePorEmail(email)) {
            throw new UsuarioJaExisteException("Já existe um supervisor com esse email.");
        }

        int id = GeradorDeId.gerarId();

        Supervisor novoSupervisor = new Supervisor(id, nome, senha, email, idade);
        repo.criar(novoSupervisor);
    }
}
