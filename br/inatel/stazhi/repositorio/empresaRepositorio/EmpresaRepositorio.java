package br.inatel.stazhi.repositorio.empresaRepositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import br.inatel.stazhi.interfaces.gerenciadorDeDados.GerenciadorDeDados;
import br.inatel.stazhi.model.empresa.Empresa;
import br.inatel.stazhi.util.dbConexao.DBConexao;

public class EmpresaRepositorio implements GerenciadorDeDados<Empresa> {

    private Connection conn;

    public EmpresaRepositorio() {
        this.conn = DBConexao.getConnection();
    }

    @Override
    public void criar(Empresa empresa) {
        String sql = "INSERT INTO empresas (nome, email, senha, cnpj, setor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getEmail());
            stmt.setString(3, empresa.getSenha());
            stmt.setString(4, empresa.getCNPJ());
            stmt.setString(5, empresa.getSetor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Empresa carregar(int id) {
        String sql = "SELECT * FROM empresas WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String cnpj = rs.getString("cnpj");
                String setor = rs.getString("setor");
                return new Empresa(id, nome, senha, email, cnpj, setor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void atualizar(Empresa empresa) {
        String sql = "UPDATE empresas SET nome = ?, email = ?, senha = ?, cnpj = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getEmail());
            stmt.setString(3, empresa.getSenha());
            stmt.setString(4, empresa.getCNPJ());
            stmt.setInt(5, empresa.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  
        }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM empresas WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
  }
