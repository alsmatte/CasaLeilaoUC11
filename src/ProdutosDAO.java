/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        boolean sucesso;
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES " + "(?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();
            sucesso = true;
            
    } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            sucesso = false;
            
        } return sucesso;
    } 
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        String sql = "SELECT id, nome, valor, status FROM produtos";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        
            ArrayList<ProdutosDTO> listagem = new ArrayList<>();
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
            return listagem;
        
        } catch (Exception e) {
            System.out.println("Erro ao trazer item: " + e.getMessage());
            return null;
        }

    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
        conn = new conectaDAO().connectDB();
        String sql = "SELECT id, nome, valor, status FROM produtos" + " WHERE status LIKE 'Vendido' ";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        
            ArrayList<ProdutosDTO> listagem = new ArrayList<>();
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
            return listagem;
        
        } catch (Exception e) {
            System.out.println("Erro ao trazer item: " + e.getMessage());
            return null;
        }

    }
    
    public void venderProduto(String id){
        
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' " + "WHERE id = ?";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, id);

            stmt.execute();
            
        } catch (Exception e) {
            System.out.println("Erro ao efetuar venda " + e.getMessage());
        }
        
    
    }
    
    
    
        
}

