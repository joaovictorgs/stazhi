package br.inatel.stazhi.cli;

import java.util.List;
import java.util.Scanner;

import br.inatel.stazhi.casodeuso.candidatura.AprovarCandidatura;
import br.inatel.stazhi.casodeuso.supervisor.SupervisionarAluno;
import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;
import br.inatel.stazhi.repositorio.vagaRepositorio.VagaRepositorio;

public class SupervisionamentoCLI {

    public static void AdicionarAlunoSupervisionado(int supervisorId){
        List<Aluno> alunos = new AlunoRepositorio().listarSemSupervisores();
        if(alunos.isEmpty()){
            System.out.println("Não existem alunos sem supervisores no momento");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Alunos Possíveis De Serem Supervisionados ===");
            for(int i =0; i<alunos.size();i++){
                Aluno aluno = alunos.get(i);
                System.out.println("\nAluno " + (i + 1) + ":");
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("Email: "+ aluno.getEmail());
            }

            System.out.println("\nDigite o número do aluno para ver mais detalhes sobre (0 para sair): ");
            int alunoSelecionado = scanner.nextInt();
            scanner.nextLine();
            if(alunoSelecionado == 0){
                break;
            }
            if(alunoSelecionado > 0 && alunoSelecionado <= alunos.size()){
                Aluno aluno = alunos.get(alunoSelecionado -1);
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("Idade: " + aluno.getIdade());
                System.out.println("Formação: " + aluno.getFormacao());
                System.out.println("Email: "+ aluno.getEmail());
            }
            System.out.println("\nDeseja supervisionar este aluno? (s/n):");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                try {
                    Aluno aluno = alunos.get(alunoSelecionado -1);
                    new SupervisionarAluno().executar(aluno.getId(),supervisorId);
                    System.out.println("Aluno aprovado com sucesso!");
                    break; // Exit candidate selection loop after approval
                } catch (Exception e) {
                    System.out.println("Erro ao tentar começar a supervisionar o aluno: " + e.getMessage());
                }
            }else{
                System.out.println("Insira um valor valido");
            }
        }
    }

}