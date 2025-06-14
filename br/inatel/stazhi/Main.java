package br.inatel.stazhi;

import br.inatel.stazhi.util.dbConexao.DBConexao;

public class Main {
    public static void main(String[] args) {
        var conexao = DBConexao.getConnection();
    }
}
