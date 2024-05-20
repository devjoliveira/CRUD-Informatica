/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.lojainformatica.dao;

import com.senac.lojainformatica.models.Computador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaov
 */
public class ComputadorDAO {
    
   public static String URL = "jdbc:mysql://localhost:3306/lojainformatica";
   public static String login = "root";
   public static String senha = "Jo673943*";
    
    
    
    public static boolean salvar(Computador obj) {
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            
            //1) Carregar o driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            
            //2) Fazer conexão com o banco
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO computador (idComputador, marca, hd, processador) VALUES(?, ?, ?, ?)");
            
            
            //3) Preparar o comando SQL
            instrucaoSQL.setInt(1, obj.getIdComputador());
            instrucaoSQL.setString(2, obj.getMarca());
            instrucaoSQL.setString(3, obj.getHd());
            instrucaoSQL.setString(4, obj.getProcessador());
            
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return retorno;
    }
    
    public static boolean alterar(Computador obj) {
        Connection conexao = null;
        boolean retorno = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE computador SET hd = ?, processador = ? WHERE idComputador = ?");

            instrucaoSQL.setString(1, obj.getHd());
            instrucaoSQL.setString(2, obj.getProcessador());
            instrucaoSQL.setInt(3, obj.getIdComputador());

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }
    
    public static boolean excluir(int id) {
        boolean retorno = false;
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM computador WHERE idComputador = ?");

            instrucaoSQL.setInt(1, id);

            int linhasAfetadas = instrucaoSQL.executeUpdate();

            retorno = linhasAfetadas > 0;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }

    public static ArrayList<Computador> listar() {
        ArrayList<Computador> list = new ArrayList<>();
        Connection conexao = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT * FROM computador");

//            comandoSQL.setInt(1, a.getNumeroNota());
//            comandoSQL.setDouble(2, a.getValorNota());
            ResultSet res = comandoSQL.executeQuery();

            while (res.next()) {

                Computador obj = new Computador(res.getInt("idComputador"), res.getString("marca"), res.getString("hd"), res.getString("processador"));
                list.add(obj);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static ArrayList<Computador> buscarPorProcessador(String processador) {
        ArrayList<Computador> list = new ArrayList<>();
        Connection conexao = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT idComputador, marca, hd, processador FROM computador WHERE processador = ?");

            comandoSQL.setString(1, processador);
            ResultSet res = comandoSQL.executeQuery();

            while (res.next()) {

                Computador obj = new Computador(res.getInt("idComputador"), res.getString("marca"), res.getString("hd"), res.getString("processador"));
                list.add(obj);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}



