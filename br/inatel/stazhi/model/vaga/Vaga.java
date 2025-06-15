package br.inatel.stazhi.model.vaga;

import java.time.LocalDate;

import br.inatel.stazhi.Enum.modalidade.Modalidade;


public class Vaga {
    private int id;
    private String descricao;
    private int quantidadeDeCandidaturas;
    private LocalDate  dataLimite;
    private Modalidade modalidade;
    private int idEmpresa;

    public Vaga(int id, String descricao, int quantidadeDeCandidaturas, LocalDate  dataLimite, Modalidade modalidade, int idEmpresa){
        this.id = id;
        this.descricao = descricao;
        this.quantidadeDeCandidaturas = quantidadeDeCandidaturas;
        this.dataLimite = dataLimite;
        this.modalidade = modalidade;
        this.idEmpresa = idEmpresa;
    }

    public int getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public int getQuantidadeDeCandidaturas(){
        return this.quantidadeDeCandidaturas;
    }

    public LocalDate  getDataLimite(){
        return this.dataLimite;
    }

    public Modalidade getModalidade(){
        return this.modalidade;
    }

    public int getIdEmpresa(){
        return this.idEmpresa;
    }
}
