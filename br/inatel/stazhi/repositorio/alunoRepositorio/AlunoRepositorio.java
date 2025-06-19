package br.inatel.stazhi.repositorio.alunoRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.inatel.stazhi.interfaces.gerenciadorComId.GerenciadorComID;
import br.inatel.stazhi.model.aluno.Aluno;
import br.inatel.stazhi.model.vaga.Vaga;
import br.inatel.stazhi.util.dbConexao.DBConexao;

public class AlunoRepositorio implements GerenciadorComID<Aluno> {
    private Connection conn;

    public AlunoRepositorio() {
        this.conn = DBConexao.getConnection();
    }
    
    @Override
    public void criar(Aluno aluno) {
        String sql = "INSERT INTO alunos (id, nome, email, senha, formacao, idade, empresas_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getId());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getSenha());
            stmt.setString(5, aluno.getFormacao());
            stmt.setInt(6, aluno.getIdade());
            stmt.setObject(7, null); // empresas_id initially null
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Aluno carregar(int id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String formacao = rs.getString("formacao");
                int idade = rs.getInt("idade");
                return new Aluno(id, nome, senha, idade, formacao, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Aluno> listarSemSupervisores() {
        String sql = "SELECT * FROM alunos WHERE supervisor_id IS NULL";
        List<Aluno> alunos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                int id =  rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String formacao = rs.getString("formacao");
                int idade = rs.getInt("idade");
                Aluno aluno = new Aluno(id, nome, senha, idade, formacao, email);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    @Override
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, email = ?, senha = ?, formacao = ?, idade = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());
            stmt.setString(4, aluno.getFormacao());
            stmt.setInt(5, aluno.getIdade());
            stmt.setInt(6, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEmpresaId(int alunoId, int empresaId) {
        String sql = "UPDATE alunos SET empresas_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empresaId);
            stmt.setInt(2, alunoId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aluno com ID " + alunoId + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar empresas_id para o aluno: " + e.getMessage());
        }
    }
    public void atualizarSupervisorId(int alunoId, int supervisorId) {
        String sql = "UPDATE alunos SET supervisor_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supervisorId);
            stmt.setInt(2, alunoId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aluno com ID " + alunoId + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar supervisor_id para o aluno: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Aluno buscarPorEmail(String email) {
        String sql = "SELECT * FROM alunos WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String formacao = rs.getString("formacao");
                int idade = rs.getInt("idade");
                return new Aluno(id, nome, senha, idade, formacao, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existePorEmail(String email) {
        String sql = "SELECT COUNT(*) FROM alunos WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}