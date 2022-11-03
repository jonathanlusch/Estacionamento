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
import java.sql.SQLException;
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
    
}

