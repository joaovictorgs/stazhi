package br.inatel.stazhi.casodeuso.empresa;

import java.time.LocalDate;

import br.inatel.stazhi.Enum.modalidade.Modalidade;
import br.inatel.stazhi.execoes.dadosInvalidosException.DadosInvalidosException;
import br.inatel.stazhi.model.vaga.Vaga;
import br.inatel.stazhi.repositorio.vagaRepositorio.VagaRepositorio;
import br.inatel.stazhi.util.geradorDeId.GeradorDeId;


public class CriarVaga {
    public void executar(int idEmpresa, String descricao, LocalDate dataLimite, Modalidade modalidade) throws DadosInvalidosException{
        var repo = new VagaRepositorio();
        int id = GeradorDeId.gerarId();
        Vaga novaVaga = new Vaga(id, descricao, 0, dataLimite, modalidade, idEmpresa);
        repo.criar(novaVaga);
    }
}
