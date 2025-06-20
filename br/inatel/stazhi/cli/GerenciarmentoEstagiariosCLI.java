package br.inatel.stazhi.cli;

import java.util.List;
import java.util.Scanner;

import br.inatel.stazhi.casodeuso.aluno.ListarEstagiarios;
import br.inatel.stazhi.casodeuso.aluno.RemoverEstagiario;
import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.model.empresa.Empresa;
import br.inatel.stazhi.model.supervisor.Supervisor;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;

public class GerenciarmentoEstagiariosCLI {

    public static void VerEstagiarios(int idEmpresa) {
        Scanner scanner = new Scanner(System.in);
        ListarEstagiarios listarEstagiarios = new ListarEstagiarios();
        RemoverEstagiario removerEstagiario = new RemoverEstagiario();

        List<Aluno> estagiarios = listarEstagiarios.executar(idEmpresa);

        if (estagiarios.isEmpty()) {
            System.out.println("Nenhum estagiário encontrado para a empresa com ID: " + idEmpresa);
            return;
        }

        System.out.println("Estagiários associados à empresa com ID: " + idEmpresa);
        for (int i = 0; i < estagiarios.size(); i++) {
            Aluno estagiario = estagiarios.get(i);
            System.out.println("[" + (i + 1) + "] ID: " + estagiario.getId()
                    + ", Nome: " + estagiario.getNome()
                    + ", Email: " + estagiario.getEmail());
        }

        System.out.println("\nDeseja remover algum estagiário?");
        System.out.println("Digite o número correspondente ao estagiário (ou 0 para voltar): ");
        int escolha = -1;

        try {
            escolha = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Operacao cancelada.");
        }

        if (escolha == 0) {
            System.out.println("Nenhum estagiário foi removido.");
        }

        if (escolha < 1 || escolha > estagiarios.size()) {
            System.out.println("Indice fora do intervalo. Operacao cancelada.");
        }

        Aluno selecionado = estagiarios.get(escolha - 1);
        removerEstagiario.executar(selecionado.getId());
        System.out.println("Estagiário " + selecionado.getNome() + " foi removido da empresa.");
    }

    public static void VerEmpresa(int alunoId) {
            Empresa empresa =  new AlunoRepositorio().buscaEmpresa(alunoId);
            if(empresa == null){
                System.out.println("\nVocê não está numa empresa no momento\n");
                return;
            }
            System.out.println("\n=== Empresa ===");
            System.out.println("Empresa: " + empresa.getNome());
            System.out.println("Setor: " + empresa.getSetor());
            System.out.println("Email: " + empresa.getEmail() +"\n");
    }
}
