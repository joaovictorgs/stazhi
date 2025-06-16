package br.inatel.stazhi.casodeuso.candidatura;

import br.inatel.stazhi.repositorio.candidaturaRepositorio.CandidaturaRepositorio;

public class AprovarCandidatura {
    
    public void executar(int idAluno, int idVaga) throws Exception {
        CandidaturaRepositorio candidaturaRepositorio = new CandidaturaRepositorio();
        
        if (!candidaturaRepositorio.existePorId(idVaga, idAluno)) {
            throw new Exception("Candidatura não encontrada para o aluno e vaga especificados.");
        }
        
        candidaturaRepositorio.deletarPorVaga(idVaga);
        
    }
}