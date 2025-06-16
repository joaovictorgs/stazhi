package br.inatel.stazhi.casodeuso.candidatura;

import br.inatel.stazhi.model.vaga.Vaga;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;
import br.inatel.stazhi.repositorio.candidaturaRepositorio.CandidaturaRepositorio;
import br.inatel.stazhi.repositorio.vagaRepositorio.VagaRepositorio;

public class AprovarCandidatura {
    
    public void executar(int idAluno, int idVaga) throws Exception {
        CandidaturaRepositorio candidaturaRepositorio = new CandidaturaRepositorio();
        VagaRepositorio vagaRepositorio = new VagaRepositorio();
        AlunoRepositorio alunoRepositorio = new AlunoRepositorio();
        
        if (!candidaturaRepositorio.existePorId(idVaga, idAluno)) {
            throw new Exception("Candidatura não encontrada para o aluno e vaga especificados.");
        }
        
        Vaga vaga = vagaRepositorio.carregar(idVaga);
        if (vaga == null) {
            throw new Exception("Vaga não encontrada.");
        }
        int empresaId = vaga.getIdEmpresa();
        
        alunoRepositorio.atualizarEmpresaId(idAluno, empresaId);
        
        candidaturaRepositorio.deletarPorVaga(idVaga);
        
        vagaRepositorio.deletar(idVaga);
    }
}