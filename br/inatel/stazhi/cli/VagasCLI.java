package br.inatel.stazhi.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.inatel.stazhi.Enum.modalidade.Modalidade;
import br.inatel.stazhi.casodeuso.candidatura.CriarCandidatura;
import br.inatel.stazhi.casodeuso.candidatura.AprovarCandidatura;
import br.inatel.stazhi.casodeuso.empresa.CriarVaga;
import br.inatel.stazhi.execoes.vagaJaExisteException.VagaJaExisteException;
import br.inatel.stazhi.model.vaga.Vaga;
import br.inatel.stazhi.repositorio.vagaRepositorio.VagaRepositorio;
import br.inatel.stazhi.model.candidatura.Candidatura;
import br.inatel.stazhi.repositorio.alunoRepositorio.AlunoRepositorio;
import br.inatel.stazhi.repositorio.candidaturaRepositorio.CandidaturaRepositorio;
import br.inatel.stazhi.model.aluno.Aluno;

public class VagasCLI {
    
    public static void Criar(int idEmpresa) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a descrição da vaga: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite a data limite da vaga no estilo DD/MM/AAAA");
        String data = scanner.nextLine();
        LocalDate dataFinal = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Selecione a modalidade 1-presencial 2-remoto 3-hibrido");
        int selecModalidade = scanner.nextInt();
        Modalidade modalidade;
        if (selecModalidade == 1) {
            modalidade = Modalidade.presencial;
        } else if (selecModalidade == 2) {
            modalidade = Modalidade.remoto;
        } else if (selecModalidade == 3) {
            modalidade = Modalidade.hibrido;
        } else { 
            System.out.println("Valor de modalidade não está correto");
            
            return;
        }
        try {
            new CriarVaga().executar(idEmpresa, descricao, dataFinal, modalidade);
            System.out.println("Vaga criada com sucesso!");  
        } catch (Exception e) {
            System.out.println("ocorreu um erro " + e.getMessage());
        }

        
    }

    public static void Listar() {
        List<Vaga> vagas = new VagaRepositorio().listarVagas();
        if (vagas.isEmpty()) {
            System.out.println("Nenhuma vaga disponível no momento.");
            return;
        }
        System.out.println("=== Vagas Disponíveis ===");
        for (int i = 0; i < vagas.size(); i++) {
            Vaga vaga = vagas.get(i);
            System.out.println("\nVaga " + (i + 1) + ":");
            System.out.println("ID: " + vaga.getId());
            System.out.println("Descrição: " + vaga.getDescricao());
            System.out.println("Quantidade de Candidaturas: " + vaga.getQuantidadeDeCandidaturas());
            System.out.println("Data Limite: " + vaga.getDataLimite().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Modalidade: " + vaga.getModalidade());
        }
    }

    public static void Listar(int idEmpresa) {
        List<Vaga> vagas = new VagaRepositorio().listarVagasPorEmpresa(idEmpresa);
        if (vagas.isEmpty()) {
            System.out.println("Nenhuma vaga disponível no momento.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Suas Vagas ===");
        for (int i = 0; i < vagas.size(); i++) {
            Vaga vaga = vagas.get(i);
            System.out.println("\nVaga " + (i + 1) + ":");
            System.out.println("ID: " + vaga.getId());
            System.out.println("Descrição: " + vaga.getDescricao());
            System.out.println("Quantidade de Candidaturas: " + vaga.getQuantidadeDeCandidaturas());
            System.out.println("Data Limite: " + vaga.getDataLimite().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Modalidade: " + vaga.getModalidade());
        }
        while (true) {
            System.out.println("\nDigite o número da vaga para ver os candidatos (0 para sair): ");
            int vagaSelecionada = scanner.nextInt();
            if (vagaSelecionada == 0) {
                break;
            }
            if (vagaSelecionada > 0 && vagaSelecionada <= vagas.size()) {
                Vaga vaga = vagas.get(vagaSelecionada - 1);
                mostrarCandidatos(vaga.getId());
            } else {
                System.out.println("Número de vaga inválido.");
            }
        }

         
    }

    private static void mostrarCandidatos(int idVaga) {
        CandidaturaRepositorio candidaturaRepositorio = new CandidaturaRepositorio();
        List<Candidatura> candidaturas = candidaturaRepositorio.listarCandidaturasPorVaga(idVaga);
        if (candidaturas.isEmpty()) {
            System.out.println("Nenhuma candidatura para esta vaga.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        AlunoRepositorio usuarioRepositorio = new AlunoRepositorio();
        System.out.println("=== Candidatos da Vaga ===");
        for (int i = 0; i < candidaturas.size(); i++) {
            Candidatura candidatura = candidaturas.get(i);
            Aluno candidato = usuarioRepositorio.carregar(candidatura.getAlunoId());
            System.out.println("\nCandidato " + (i + 1) + ":");
            System.out.println("ID: " + candidatura.getAlunoId());
            System.out.println("Nome: " + (candidato != null ? candidato.getNome() : "Desconhecido"));
            System.out.println("Email: " + (candidato != null ? candidato.getEmail() : "Desconhecido"));
        }
        while (true) {
            System.out.println("\nDigite o número do candidato para ver detalhes ou aprovar (0 para voltar): ");
            int candidatoSelecionado = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (candidatoSelecionado == 0) {
                break;
            }
            if (candidatoSelecionado > 0 && candidatoSelecionado <= candidaturas.size()) {
                Candidatura candidatura = candidaturas.get(candidatoSelecionado - 1);
                Aluno candidato = usuarioRepositorio.carregar(candidatura.getAlunoId());
                if (candidato != null) {
                    System.out.println("\n=== Detalhes do Candidato ===");
                    System.out.println("ID: " + candidatura.getAlunoId());
                    System.out.println("Nome: " + candidato.getNome());
                    System.out.println("Email: " + candidato.getEmail());
                    System.out.println("\nDeseja aprovar este candidato? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s")) {
                        try {
                            new AprovarCandidatura().executar(candidatura.getAlunoId(), idVaga);
                            System.out.println("Candidato aprovado com sucesso! Todas as candidaturas para esta vaga foram excluídas.");
                            break; // Exit candidate selection loop after approval
                        } catch (Exception e) {
                            System.out.println("Erro ao aprovar candidato: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("Candidato não encontrado.");
                }
            } else {
                System.out.println("Número de candidato inválido.");
            }
            
        }

        
    }

    public static void inscreverNaVaga(int idAluno) throws VagaJaExisteException {
        Listar();
        List<Vaga> vagas = new VagaRepositorio().listarVagas();
        int nVagas = vagas.size();

        Scanner scanner = new Scanner(System.in);
        System.out.println("selecione o numero da vaga que deseja se cadastrar");
        int vagaSelecionada = scanner.nextInt();
        if (vagaSelecionada > 0 && vagaSelecionada <= nVagas) {
            vagaSelecionada -= 1;
            int idVaga = vagas.get(vagaSelecionada).getId();
            new CriarCandidatura().executar(idAluno, idVaga);
        } else {
            System.out.println("Valor inválido");
            
            return;
        }
        System.out.println("Candidatou com sucesso na vaga");

        
    }
}