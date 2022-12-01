/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Motorista;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MotoristaDAO {

    public void create(Motorista m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO motorista (nome, genero, RG, CPF, celular, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getGenero());
            stmt.setInt(3, m.getRG());
            stmt.setInt(4, m.getCPF());
            stmt.setInt(5, m.getCelular());
            stmt.setString(6, m.getEmail());
            stmt.setString(7, m.getSenha());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Motorista salvo com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Motorista> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt= null;
        ResultSet rs = null;
        List<Motorista> vagas = new ArrayList<>();
        try {                            
            stmt =con.prepareStatement("SELECT * FROM vaga;");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Motorista m = new Motorista();
                m.setIdVaga(rs.getInt("IdVaga"));
                m.setNumero(rs.getInt("numero"));
                m.setRua(rs.getString("Rua"));
                m.setObliqua(rs.getBoolean("Obliqua"));
                vagas.add(m);
                
            }
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os dados:", e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return vagas;

            
        }

        public void delete(Motorista m) {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            try{
                stmt = con.prepareStatement("DELETE FROM vaga WHERE idVaga=?");
                stmt.setInt(1,m.getIdMotorista());
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Vaga excluída com sucesso");
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
            }finally {
                ConnectionFactory.closeConnection(con, stmt);
            }
        }
        
      public Motorista read(int idMotorista){
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         Motorista m = new Motorista();
         try{
             stmt = con.prepareStatement("Select * FROM vaga where idMotorista=? LIMIT 1;");
             stmt.setInt(1, idMotorista);
             rs = stmt.executeQuery();
             if(rs != null && rs.next()){
                m.setIdMotorista(rs.getInt("IdMotorista"));
                m.setNome(rs.getString("Nome"));
                m.setGenero(rs.getString("Genêro"));
                m.setRG(rs.getInt("RG"));
                m.setCPF(rs.getInt("CPF"));
                m.setCelular(rs.getInt("Celular"));
                m.setEmail(rs.getString("Email"));
             }
         } catch(SQLException e){
             throw new RuntimeException("Erro ao buscar os dados", e);
         } finally{
             ConnectionFactory.closeConnection(con, stmt, rs);
         }
         return m;
     }
     public void update(Motorista m){
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         try{
             stmt = con.prepareCall("UPDATE vaga SET nome=?, homem=?, rg=?, cpf=?, celular=?, email=? WHERE idMotorista=?");
             stmt.setString(1, m.getNome());
             stmt.setString(2, m.getGenero());
             stmt.setInt(3, m.getRG());
             stmt.setInt(5, m.getCPF());
             stmt.setInt(6, m.getCelular());
             stmt.setString(7, m.getEmail());
             stmt.executeUpdate();
             JOptionPane.showMessageDialog(null,"Motorista Atualizado com sucesso!");

         }catch (SQLException e ){
             JOptionPane.showMessageDialog(null,"Erro ao atualizar: " + e);
         }finally{
             ConnectionFactory.closeConnection(con, stmt);
         }
     }
    
}

