package br.inatel.stazhi.model.candidatura;

public class Candidatura {
    private int vagaId;
    private int alunoId;

    public Candidatura(int vagaId, int alunoId){
        this.vagaId = vagaId;
        this.alunoId = alunoId;
    }

    public int getVagaId(){
        return this.vagaId;
    }

    public int getAlunoId(){
        return this.alunoId;
    }
    
}
