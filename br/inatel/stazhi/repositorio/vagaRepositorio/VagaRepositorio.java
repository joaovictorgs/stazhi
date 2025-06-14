package br.inatel.stazhi.repositorio.vagaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.inatel.stazhi.Enum.modalidade.Modalidade;
import br.inatel.stazhi.interfaces.gerenciadorComId.GerenciadorComID;
import br.inatel.stazhi.model.vaga.Vaga;
import br.inatel.stazhi.util.dbConexao.DBConexao;

public class VagaRepositorio implements GerenciadorComID<Vaga> {
    private Connection conn;

    public VagaRepositorio() {
        this.conn = DBConexao.getConnection();
    }

    @Override
    public void criar(Vaga vaga) {
        String sql = "INSERT INTO vagas (id ,descricao, quantidade_de_candidaturas, data_limite, modalidade, empresas_id) VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vaga.getId());
            stmt.setString(2, vaga.getDescricao());
            stmt.setInt(3, vaga.getQuantidadeDeCandidaturas());
            stmt.setDate(4, java.sql.Date.valueOf(vaga.getDataLimite()));
            stmt.setString(5, vaga.getModalidade().toString());
            stmt.setInt(6, vaga.getIdEmpresa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vaga carregar(int id) {
        String sql = "SELECT * FROM vagas WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String descricao = rs.getString("descricao");
                LocalDate dataLimite = rs.getDate("data_limite").toLocalDate();
                int quantidadeDeCandidaturas = rs.getInt("quantidade_de_candidaturas");
                String modalidadeStr = rs.getString("modalidade");
                Modalidade modalidade = Modalidade.valueOf(modalidadeStr);
                int idEmpresa = rs.getInt("empresas_id"); 

                return new Vaga(id, descricao, quantidadeDeCandidaturas, dataLimite, modalidade, idEmpresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vaga> listarVagas(){
        String sql = "SELECT * FROM vagas";
         List<Vaga> vagas = new ArrayList<>();
         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                LocalDate dataLimite = rs.getDate("data_limite").toLocalDate();
                int quantidadeDeCandidaturas = rs.getInt("quantidade_de_candidaturas");
                String modalidadeStr = rs.getString("modalidade");
                Modalidade modalidade = Modalidade.valueOf(modalidadeStr);
                int idEmpresa = rs.getInt("empresas_id");

                Vaga vaga = new Vaga(id, descricao, quantidadeDeCandidaturas, dataLimite, modalidade, idEmpresa);
                vagas.add(vaga);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return vagas;
    }

    @Override
    public void atualizar(Vaga vaga) {
        String sql = "UPDATE vagas SET descricao = ?, quantidade_de_candidaturas = ?, data_limite = ?, modalidade = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vaga.getDescricao());
            stmt.setInt(2, vaga.getQuantidadeDeCandidaturas());
            stmt.setDate(3, java.sql.Date.valueOf(vaga.getDataLimite()));
            stmt.setString(4, vaga.getModalidade().toString());
            stmt.setInt(5, vaga.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM vagas WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vaga> listarVagasPorEmpresa(int idEmpresa) {
        String sql = "SELECT * FROM vagas WHERE empresas_id = ?";
        List<Vaga> vagas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                LocalDate dataLimite = rs.getDate("data_limite").toLocalDate();
                int quantidadeDeCandidaturas = rs.getInt("quantidade_de_candidaturas");
                String modalidadeStr = rs.getString("modalidade");
                Modalidade modalidade = Modalidade.valueOf(modalidadeStr);

                Vaga vaga = new Vaga(id, descricao, quantidadeDeCandidaturas, dataLimite, modalidade, idEmpresa);
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vagas;
    }
}
