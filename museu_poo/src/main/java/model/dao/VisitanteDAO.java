package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Visitante;

public class VisitanteDAO {

    public Visitante checkLogin(String email, String senha) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Visitante v = null;

        try {
            String sql = "SELECT * FROM visitante WHERE emailVisitante = ? AND senha = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Visitante();
                v.setIdVisitiante(rs.getInt("idVisitante"));
                v.setNomeVisitante(rs.getString("nomeVisitante"));
                v.setEmailVisitante(rs.getString("emailVisitante"));
                v.setTipoUsuario(rs.getString("tipoUsuario"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Login: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return v;
    }

    public void create(Visitante v) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO visitante(nomeVisitante, emailVisitante, telVisitante, dataNasc, senha, tipoUsuario) VALUES(?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, v.getNomeVisitante());
            stmt.setString(2, v.getEmailVisitante());
            stmt.setString(3, v.getTelVisitante());
            stmt.setDate(4, v.getDataNasc());
            stmt.setString(5, v.getSenha());
            stmt.setString(6, "VISITANTE");

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Visitante cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Visitante. Erro: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public ArrayList<Visitante> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Visitante> listaVisitantes = new ArrayList<>();

        try {
            String query = "SELECT * FROM visitante";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Visitante v = new Visitante();
                v.setIdVisitiante(rs.getInt("idVisitante"));
                v.setNomeVisitante(rs.getString("nomeVisitante"));
                v.setEmailVisitante(rs.getString("emailVisitante"));
                v.setTelVisitante(rs.getString("telVisitante"));
                v.setDataNasc(rs.getDate("dataNasc"));
                v.setTipoUsuario(rs.getString("tipoUsuario"));
                listaVisitantes.add(v);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar visitantes. Erro: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return listaVisitantes;
    }

    public Visitante read(int idVisitante) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM visitante WHERE idVisitante = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idVisitante);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Visitante v = new Visitante();
                v.setIdVisitiante(rs.getInt("idVisitante"));
                v.setNomeVisitante(rs.getString("nomeVisitante"));
                v.setEmailVisitante(rs.getString("emailVisitante"));
                v.setTelVisitante(rs.getString("telVisitante"));
                v.setDataNasc(rs.getDate("dataNasc"));
                v.setTipoUsuario(rs.getString("tipoUsuario"));
                return v;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar visitante. Erro: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return null;
    }

    public void update(Visitante v) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            String query = "UPDATE visitante SET nomeVisitante = ?, emailVisitante = ?, telVisitante = ?, dataNasc = ? WHERE idVisitante = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, v.getNomeVisitante());
            stmt.setString(2, v.getEmailVisitante());
            stmt.setString(3, v.getTelVisitante());
            stmt.setDate(4, v.getDataNasc());
            stmt.setInt(5, v.getIdVisitiante());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar dados. Erro: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public void destroy(Visitante v) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            String query = "DELETE FROM visitante WHERE idVisitante = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, v.getIdVisitiante());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Visitante excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir visitante. Erro: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}