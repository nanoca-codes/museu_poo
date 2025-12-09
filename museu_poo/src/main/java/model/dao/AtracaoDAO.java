package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Atracao;

public class AtracaoDAO {
    
    public void create (Atracao a){
      Connection con = Conexao.getConexao();
      PreparedStatement stmt = null;
      
      try {
          String query = "INSERT INTO atracao (nomeAtracao, descricao) VALUES(?,?)";
          stmt = con.prepareStatement(query);
          stmt.setString(1, a.getNomeAtracao());
          stmt.setString(2, a.getDescricao());
          stmt.executeUpdate();
          
          JOptionPane.showMessageDialog(null, "Atração cadastrada com sucesso!");
      }
      catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Falha ao cadastrar atração. Erro: " + ex.getMessage());
      }
      finally {
          Conexao.fecharConexao(con, stmt);
      }
    }
    
    public ArrayList<Atracao> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Atracao> listaAtracoes = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM atracao";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Atracao a = new Atracao();
                a.setIdAtracao(rs.getInt("idAtracao"));
                a.setNomeAtracao(rs.getString("nomeAtracao"));
                a.setDescricao(rs.getString("descricao"));
                listaAtracoes.add(a);
            }
            return listaAtracoes;
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar atrações. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return null;
    }
    
    public Atracao read (int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM atracao WHERE idAtracao = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                Atracao a = new Atracao();
                a.setIdAtracao(rs.getInt("idAtracao"));
                a.setNomeAtracao(rs.getString("nomeAtracao"));
                a.setDescricao(rs.getString("descricao"));
                return a;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar atração. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return null;
    }
    
    public void update(Atracao a) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE atracao SET nomeAtracao = ?, descricao = ? WHERE idAtracao = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getNomeAtracao());
            stmt.setString(2, a.getDescricao());
            stmt.setInt(3, a.getIdAtracao());
            
           stmt.executeUpdate();
           
           JOptionPane.showMessageDialog(null, "Atração editada com sucesso");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao editar atração. Erro " + ex.getMessage());          
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy (Atracao a){
       Connection con = Conexao.getConexao();
       PreparedStatement stmt = null;
       
       try {
           String query = "DELETE FROM atracao WHERE idAtracao = ?";
           stmt = con.prepareStatement(query);
           stmt.setInt(1, a.getIdAtracao());
           
           stmt.executeUpdate();
           
           JOptionPane.showMessageDialog(null, "Atração excluída com sucesso");
       }
       catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Falha ao excluir atração. Erro: " + ex.getMessage());
       }
       finally{
           Conexao.fecharConexao(con, stmt);
       }
   }
}