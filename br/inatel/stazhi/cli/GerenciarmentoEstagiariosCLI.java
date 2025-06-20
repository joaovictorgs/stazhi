package br.inatel.stazhi.cli;

import java.util.List;
import java.util.Scanner;

import br.inatel.stazhi.casodeuso.aluno.ListarEstagiarios;
import br.inatel.stazhi.casodeuso.aluno.RemoverEstagiario;
import br.inatel.stazhi.model.aluno.Aluno;

public class GerenciarmentoEstagiariosCLI {

    public static void VerEstagiarios(int idEmpresa) {
        Scanner scanner = new Scanner(System.in);
        ListarEstagiarios listarEstagiarios = new ListarEstagiarios();
        RemoverEstagiario removerEstagiario = new RemoverEstagiario();

        List<Aluno> estagiarios = listarEstagiarios.executar(idEmpresa);

        if (estagiarios.isEmpty()) {
            System.out.println("Nenhum estagiário encontrado para a empresa com ID: " + idEmpresa);
            scanner.close();
            return;
        }

        System.out.println("Estagiários associados à empresa com ID: " + idEmpresa);
        for (Aluno estagiario : estagiarios) {
            System.out.println("ID: " + estagiario.getId() + ", Nome: " + estagiario.getNome() + ", Email: " + estagiario.getEmail());
        }

        System.out.println("\nDeseja remover algum estagiário? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            System.out.print("Digite o ID do estagiário que deseja remover: ");
            int idEstagiario = scanner.nextInt();
            scanner.nextLine();

            removerEstagiario.executar(idEstagiario);
            System.out.println("Estagiário com ID " + idEstagiario + " foi removido da empresa.");
        }

      scanner.close();
    }
}
