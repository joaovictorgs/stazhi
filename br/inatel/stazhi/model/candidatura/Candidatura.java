package br.inatel.stazhi.model.candidatura;

import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.model.vaga.Vaga;

public class Candidatura {
    private Vaga vaga;
    private Aluno aluno;

    public Candidatura(Vaga vaga, Aluno aluno){
        this.vaga = vaga;
        this.aluno = aluno;
    }

    public Vaga getVaga(){
        return this.vaga;
    }

    public Aluno getAluno(){
        return this.aluno;
    }
}
