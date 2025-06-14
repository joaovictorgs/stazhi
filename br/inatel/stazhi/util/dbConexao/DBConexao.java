package br.inatel.stazhi.util.dbConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/stazhi";
    private static final String USER = "stazhi";
    private static final String PASSWORD = "stazhi";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco", e);
        }
    }
}