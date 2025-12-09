package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Guia;

public class GuiaDAO {
    
    public void create(Guia g) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "INSERT INTO guia (nomeGuia, especialidade, emailGuia) VALUES (?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, g.getNomeGuia());
            stmt.setString(2, g.getEspecialidade());
            stmt.setString(3, g.getEmailGuia());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Guia cadastrado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar guia. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Guia> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Guia> listaGuias = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM guia";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Guia g = new Guia();
                g.setIdGuia(rs.getInt("idGuia"));
                g.setNomeGuia(rs.getString("nomeGuia"));
                g.setEspecialidade(rs.getString("especialidade"));
                g.setEmailGuia(rs.getString("emailGuia"));
                listaGuias.add(g);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar guias. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return listaGuias;
    }
    
    public Guia read(int idGuia) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM guia WHERE idGuia = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idGuia);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                Guia g = new Guia();
                g.setIdGuia(rs.getInt("idGuia"));
                g.setNomeGuia(rs.getString("nomeGuia"));
                g.setEspecialidade(rs.getString("especialidade"));
                g.setEmailGuia(rs.getString("emailGuia"));
                return g;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar guia. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return null;
    }
    
    public void update(Guia g) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE guia SET nomeGuia = ?, especialidade = ?, emailGuia = ? WHERE idGuia = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, g.getNomeGuia());
            stmt.setString(2, g.getEspecialidade());
            stmt.setString(3, g.getEmailGuia());
            stmt.setInt(4, g.getIdGuia());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Guia atualizado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar guia. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy(Guia g) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM guia WHERE idGuia = ?";
            stmt = con.prepareStatement(query);            
            stmt.setInt(1, g.getIdGuia());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Guia excluído com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir guia. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}