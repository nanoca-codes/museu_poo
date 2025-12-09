package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Reserva;
import model.bean.ReservaAtracao;
import model.bean.ReservaGuia;
import model.bean.Visitante;

public class ReservaDAO {

    public boolean criarReservaCompleta(Reserva r) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sqlReserva = "INSERT INTO reserva(dataReserva, horario, status, idVisitante) VALUES (?,?,?,?)";
        String sqlAtracao = "INSERT INTO reservaatracao(idReserva, idAtracao, horarioVisita) VALUES (?,?,?)";
        String sqlGuia = "INSERT INTO reservaguia(idReserva, idGuia) VALUES (?,?)";
        
        try {
            con.setAutoCommit(false);

            stmt = con.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, r.getDataReserva());
            stmt.setTime(2, r.getHorario());
            stmt.setString(3, "ativa");
            stmt.setInt(4, r.getVisitante().getIdVisitiante());
            stmt.executeUpdate();
            
            rs = stmt.getGeneratedKeys();
            int idReservaGerado = 0;
            if(rs.next()){
                idReservaGerado = rs.getInt(1);
            }

            for(ReservaAtracao ra : r.getAtracoes()){
                stmt = con.prepareStatement(sqlAtracao);
                stmt.setInt(1, idReservaGerado);
                stmt.setInt(2, ra.getAtracao().getIdAtracao());
                stmt.setTime(3, ra.getHorarioVisita());
                stmt.executeUpdate();
            }
            
            for(ReservaGuia rg : r.getGuias()){
                stmt = con.prepareStatement(sqlGuia);
                stmt.setInt(1, idReservaGerado);
                stmt.setInt(2, rg.getGuia().getIdGuia());
                stmt.executeUpdate();
            }

            con.commit();
            JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
            return true;

        } catch(SQLException ex) {
            try { con.rollback(); } catch (SQLException e) {}
            JOptionPane.showMessageDialog(null, "Erro ao realizar reserva: " + ex.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
    }
    
    public ArrayList<Reserva> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Reserva> listaReservas = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM reserva";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()) {                
                Reserva r = new Reserva();
                r.setIdReserva(rs.getInt("idReserva"));
                r.setDataReserva(rs.getDate("dataReserva"));
                r.setHorario(rs.getTime("horario"));
                r.setStatus(rs.getString("status"));
                
                Visitante v = new VisitanteDAO().read(rs.getInt("idVisitante"));
                r.setVisitante(v);
                
                listaReservas.add(r);
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return listaReservas;
    }
    
    public boolean update(Reserva r) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            con.setAutoCommit(false); // Inicia Transação

            // 1. Atualiza dados da Reserva (Data/Hora)
            String sqlReserva = "UPDATE reserva SET dataReserva = ?, horario = ? WHERE idReserva = ?";
            stmt = con.prepareStatement(sqlReserva);
            stmt.setDate(1, r.getDataReserva());
            stmt.setTime(2, r.getHorario());
            stmt.setInt(3, r.getIdReserva());
            stmt.executeUpdate();

            // 2. Atualiza Atração (Estratégia: Remove a antiga e insere a nova)
            // Remove antigos
            stmt = con.prepareStatement("DELETE FROM reservaatracao WHERE idReserva = ?");
            stmt.setInt(1, r.getIdReserva());
            stmt.executeUpdate();
            
            // Insere novos (se houver)
            String sqlAtracao = "INSERT INTO reservaatracao(idReserva, idAtracao, horarioVisita) VALUES (?,?,?)";
            for(ReservaAtracao ra : r.getAtracoes()){
                stmt = con.prepareStatement(sqlAtracao);
                stmt.setInt(1, r.getIdReserva());
                stmt.setInt(2, ra.getAtracao().getIdAtracao());
                stmt.setTime(3, ra.getHorarioVisita());
                stmt.executeUpdate();
            }

            // 3. Atualiza Guia (Estratégia: Remove o antigo e insere o novo)
            // Remove antigos
            stmt = con.prepareStatement("DELETE FROM reservaguia WHERE idReserva = ?");
            stmt.setInt(1, r.getIdReserva());
            stmt.executeUpdate();
            
            // Insere novos (se houver)
            String sqlGuia = "INSERT INTO reservaguia(idReserva, idGuia) VALUES (?,?)";
            for(ReservaGuia rg : r.getGuias()){
                stmt = con.prepareStatement(sqlGuia);
                stmt.setInt(1, r.getIdReserva());
                stmt.setInt(2, rg.getGuia().getIdGuia());
                stmt.executeUpdate();
            }

            con.commit(); // Confirma tudo
            JOptionPane.showMessageDialog(null, "Reserva editada com sucesso!");
            return true;

        } catch(SQLException ex) {
            try { con.rollback(); } catch (SQLException e) {}
            JOptionPane.showMessageDialog(null, "Erro ao editar: " + ex.getMessage());
            return false;
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy(Reserva r) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM reserva WHERE idReserva = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, r.getIdReserva());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Reserva excluída com sucesso!");            
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public String buscarNomeAtracao(int idReserva) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT a.nomeAtracao FROM atracao a " +
                         "INNER JOIN reservaatracao ra ON a.idAtracao = ra.idAtracao " +
                         "WHERE ra.idReserva = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idReserva);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nomeAtracao");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return " - "; 
    }

    public String buscarNomeGuia(int idReserva) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT g.nomeGuia FROM guia g " +
                         "INNER JOIN reservaguia rg ON g.idGuia = rg.idGuia " +
                         "WHERE rg.idReserva = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idReserva);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nomeGuia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return " - ";
    }
    
    
}