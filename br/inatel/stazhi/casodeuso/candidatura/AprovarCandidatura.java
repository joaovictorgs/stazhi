package br.inatel.stazhi.casodeuso.candidatura;

import br.inatel.stazhi.repositorio.candidaturaRepositorio.CandidaturaRepositorio;
import br.inatel.stazhi.repositorio.vagaRepositorio.VagaRepositorio;

public class AprovarCandidatura {
    
    public void executar(int idAluno, int idVaga) throws Exception {
        CandidaturaRepositorio candidaturaRepositorio = new CandidaturaRepositorio();
        VagaRepositorio vagaRepositorio = new VagaRepositorio();
        
        candidaturaRepositorio.deletarPorVaga(idVaga);
        
        vagaRepositorio.deletar(idVaga);
    }
}