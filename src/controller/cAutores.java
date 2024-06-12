/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.mAutores;


/**
 *
 * @author guest01
 */
public class cAutores {

    public void cadastrar(mAutores modelE) {
        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO autores (nome,endereco,numero,bairro,cidade,cpf) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, modelE.getNome());
            stmt.setString(2, modelE.getEndereco());
            stmt.setString(3, modelE.getNumero());
            stmt.setString(4, modelE.getBairro());
            stmt.setString(5, modelE.getCidade());
            stmt.setString(6, modelE.getCpf());
               

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor(a) cadastrado com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return
     */
    public List<mAutores> listar() {

        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<mAutores> lista = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();

            while (rs.next()) {
                mAutores modelE = new mAutores();
                modelE.setId_autores(rs.getInt("id_autor"));
                modelE.setNome(rs.getString("nome"));
                modelE.setEndereco(rs.getString("endereco"));
                modelE.setNumero(rs.getString("numero"));
                modelE.setBairro(rs.getString("bairro"));
                modelE.setCidade(rs.getString("cidade"));
                modelE.setCpf(rs.getString("cpf"));

                lista.add(modelE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cAutores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

   
    public List<mAutores> pesquisar(String texto) {

        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<mAutores> lista = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM autores WHERE nome like ?");
            stmt.setString(1, "%"+ texto + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                mAutores modelE = new mAutores();
                modelE.setId_autores(rs.getInt("id_autor"));
                modelE.setNome(rs.getString("Nome"));

                lista.add(modelE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
    
    public void alterar(mAutores modelA) {

        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE autores SET nome = ? WHERE id_autor = ? ");
            stmt.setString(1, modelA.getNome());
            stmt.setInt(2, modelA.getId_autores());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor Alterado");

        } catch (SQLException ex) {
            Logger.getLogger(cAutores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
