package br.inatel.stazhi;

import java.sql.SQLException;

import br.inatel.stazhi.util.dbConexao.DBConexao;

public class Main {
    public static void main(String[] args) {
        try {
            var conexao = DBConexao.getConnection();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
