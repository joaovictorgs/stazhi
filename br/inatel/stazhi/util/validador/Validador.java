package br.inatel.stazhi.util.validador;

import java.util.regex.Pattern;

import br.inatel.stazhi.execoes.CNPJInvalidoException.CNPJInvalidoException;
import br.inatel.stazhi.execoes.dadosInvalidosException.DadosInvalidosException;

public class Validador {

    public static void CNPJ(String cnpj) throws CNPJInvalidoException{
          if (cnpj == null) throw new CNPJInvalidoException("CNPJ Error, CNPJ não inserido. O formato correto deve ser XX.XXX.XXX/0001-ZZ");

        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Verifica se tem 14 dígitos
        if (cnpj.length() != 14) throw new CNPJInvalidoException("CNPJ Error, CNPJ de tamanho inválido. O formato correto deve ser XX.XXX.XXX/0001-ZZ");

        // Verifica se todos os dígitos são iguais (ex: 00000000000000)
        if (cnpj.matches("(\\d)\\1{13}"))  throw new CNPJInvalidoException("CNPJ Error, o valores não está de acordo. O formato correto deve ser XX.XXX.XXX/0001-ZZ ");

        //verifica se é valido o cnpj no caso de um cnpj no padrão passar pelas demais verificações
        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            String cnpjBase = cnpj.substring(0, 12);
            String digitos = cnpj.substring(12, 14);

            int primeiroDigito = calcularDigito(cnpjBase, pesos1);
            int segundoDigito = calcularDigito(cnpjBase + primeiroDigito, pesos2);

            if(!digitos.equals("" + primeiroDigito + segundoDigito)){
                throw new CNPJInvalidoException("CNPJ Error, CNPJ inválido. O formato correto deve ser XX.XXX.XXX/0001-ZZ");
            }
        } catch (Exception e) {
            throw new CNPJInvalidoException("CNPJ Error, CNPJ inválido. O formato correto deve ser XX.XXX.XXX/0001-ZZ");
        }
    }

    private static final Pattern EMAIL_REGEX = Pattern.compile(
        "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$"
    );

    public static void Email(String email) throws DadosInvalidosException {
        if (email == null) throw new DadosInvalidosException("Error, nenhum email foi escrito") ;

        if(!EMAIL_REGEX.matcher(email).matches()) throw new DadosInvalidosException("Error, email não se encaixa no padrão xxx@email.com") ;
    }

    


    private static int calcularDigito(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            soma += Character.getNumericValue(str.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
