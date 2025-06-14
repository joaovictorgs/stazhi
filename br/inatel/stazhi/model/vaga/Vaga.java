package br.inatel.stazhi.model.vaga;

import java.text.DateFormat;

import br.inatel.stazhi.Enum.modalidade.Modalidade;


public class Vaga {
    private int id;
    private String descricao;
    private int quantidadeDeCandidaturas;
    private DateFormat dataLimite;
    private Modalidade modalidade;

    public Vaga(int id, String descricao, int quantidadeDeCandidaturas, DateFormat dataLimite, Modalidade modalidade){
        this.id = id;
        this.descricao = descricao;
        this.quantidadeDeCandidaturas = quantidadeDeCandidaturas;
        this.dataLimite = dataLimite;
        this.modalidade = modalidade;
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

    public DateFormat getDataLimite(){
        return this.dataLimite;
    }

    public Modalidade getModalidade(){
        return this.modalidade;
    }
}
