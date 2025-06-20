package br.inatel.stazhi.casodeuso.candidatura;

import br.inatel.stazhi.execoes.vagaJaExisteException.VagaJaExisteException;
import br.inatel.stazhi.model.candidatura.Candidatura;
import br.inatel.stazhi.repositorio.candidaturaRepositorio.CandidaturaRepositorio;


public class CriarCandidatura {
    public void executar(int idAluno, int idVaga) throws VagaJaExisteException{
        var repo = new CandidaturaRepositorio();

        if (repo.existePorId(idVaga, idAluno)) {
          throw new VagaJaExisteException("Você já está candidatado nessa vaga");
      }

        Candidatura novaEmpresa = new Candidatura(idVaga, idAluno);
        repo.criar(novaEmpresa);
    }
}
