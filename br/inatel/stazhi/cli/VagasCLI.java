package br.inatel.stazhi.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.inatel.stazhi.Enum.modalidade.Modalidade;
import br.inatel.stazhi.casodeuso.empresa.CriarVaga;
import br.inatel.stazhi.model.empresa.Empresa;

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
}
