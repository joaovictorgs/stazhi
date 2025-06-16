package br.inatel.stazhi.repositorio.candidaturaRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO candidaturas (vagas_id, alunos_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, candidatura.getVagaId());
            stmt.setInt(2, candidatura.getAlunoId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();    

        }
    }

   public Candidatura carregarPorIds(int vagaId, int alunoId) {
        String sql = "SELECT * FROM candidaturas WHERE vagas_id = ? AND alunos_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "UPDATE candidaturas SET vagas_id = ?, alunos_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, candidatura.getVagaId());
            stmt.setInt(2, candidatura.getAlunoId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void deletarPorIds(int vagaId, int alunoId) {
        String sql = "DELETE FROM candidaturas WHERE vagas_id = ? AND alunos_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vagaId);
            stmt.setInt(2, alunoId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existePorId(int vagaId, int alunoId){
        String sql = "SELECT * FROM candidaturas WHERE vagas_id = ? AND alunos_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, vagaId);
            stmt.setInt(2, alunoId);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public List<Candidatura> listarCandidaturasPorVaga(int idVaga) {
        String sql = "SELECT * FROM candidaturas WHERE vagas_id = ?";
        List<Candidatura> candidaturas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                int vagaId = rs.getInt("vagas_id");
                int alunoId = rs.getInt("alunos_id");
                candidaturas.add(new Candidatura(vagaId, alunoId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidaturas;
    }
}
