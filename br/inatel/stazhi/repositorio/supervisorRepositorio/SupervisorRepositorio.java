package br.inatel.stazhi.repositorio.supervisorRepositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import br.inatel.stazhi.interfaces.gerenciadorComId.GerenciadorComID;
import br.inatel.stazhi.model.supervisor.Supervisor;
import br.inatel.stazhi.util.dbConexao.DBConexao;

public class SupervisorRepositorio implements GerenciadorComID<Supervisor> {

    private Connection conn;

    public SupervisorRepositorio() {
        this.conn = DBConexao.getConnection();
    }

    @Override
    public void criar(Supervisor supervisor) {
        String sql = "INSERT INTO supervisores (nome, email, senha, idade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supervisor.getNome());
            stmt.setString(2, supervisor.getEmail());
            stmt.setString(3, supervisor.getSenha());
            stmt.setInt(4, supervisor.getIdade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Supervisor carregar(int id) {
        String sql = "SELECT * FROM supervisores WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                int idade = rs.getInt("idade");
                return new Supervisor(id, nome, senha, email, idade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void atualizar(Supervisor supervisor) {
        String sql = "UPDATE supervisores SET nome = ?, email = ?, senha = ?, area = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supervisor.getNome());
            stmt.setString(2, supervisor.getEmail());
            stmt.setString(3, supervisor.getSenha());
            stmt.setInt(4, supervisor.getIdade());
            stmt.setInt(5, supervisor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM supervisores WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  }
