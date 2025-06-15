package br.inatel.stazhi.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.inatel.stazhi.Enum.modalidade.Modalidade;
import br.inatel.stazhi.casodeuso.empresa.CriarVaga;
import br.inatel.stazhi.model.empresa.Empresa;
import br.inatel.stazhi.model.vaga.Vaga;
import br.inatel.stazhi.repositorio.vagaRepositorio.VagaRepositorio;

public class VagasCLI {
    
    public static void Criar(int idEmpresa){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a descrição da vaga: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite a data limite da vaga no estilo DD/MM/AAAA");
        String data = scanner.nextLine();
        LocalDate dataFinal = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Selecione a modalidade 1-presencial 2-remoto 3-hibrido");
        int selecModalidade = scanner.nextInt();
        Modalidade modalidade;
         if(selecModalidade == 1){
            modalidade = Modalidade.presencial;
        }else if(selecModalidade == 2){
            modalidade = Modalidade.remoto;
        }else if(selecModalidade==3){
            modalidade = Modalidade.hibrido;
        }else{ 
           System.out.println("Valor de modalidade não está correto");
           return;
        }
        try {
            new CriarVaga().executar(idEmpresa,descricao,dataFinal,modalidade );
            System.out.println("Vaga criada com sucesso!");  
        } catch (Exception e) {
            System.out.println("ocorreu um erro "+ e.getMessage());
        }
    }

    public static void Listar(){
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
}
