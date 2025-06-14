package br.inatel.stazhi.repositorio.candidaturaRepositorio;

import java.sql.Connection;

import br.inatel.stazhi.interfaces.gerenciadorDeDados.GerenciadorDeDados;
import br.inatel.stazhi.model.candidatura.Candidatura;
import br.inatel.stazhi.util.dbConexao.DBConexao;

public class CandidaturaRepositorio implements GerenciadorDeDados<Candidatura> {

    private Connection conn;

    public CandidaturaRepositorio() {
        this.conn = DBConexao.getConnection();
    }

    @Override
    public void criar(Candidatura candidatura) {
        String sql = "INSERT INTO candidaturas (vaga_id, aluno_id) VALUES (?, ?)";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, candidatura.getVagaId());
            stmt.setInt(2, candidatura.getAlunoId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();    

        }
    }

   public Candidatura carregarPorIds(int vagaId, int alunoId) {
        String sql = "SELECT * FROM candidaturas WHERE vaga_id = ? AND aluno_id = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vagaId);
            stmt.setInt(2, alunoId);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Candidatura(vagaId, alunoId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void atualizar(Candidatura candidatura) {
        String sql = "UPDATE candidaturas SET vaga_id = ?, aluno_id = ? WHERE id = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, candidatura.getVagaId());
            stmt.setInt(2, candidatura.getAlunoId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void deletarPorIds(int vagaId, int alunoId) {
        String sql = "DELETE FROM candidaturas WHERE vaga_id = ? AND aluno_id = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vagaId);
            stmt.setInt(2, alunoId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
