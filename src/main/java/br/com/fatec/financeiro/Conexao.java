/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.financeiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author junior
 */
public class Conexao {
    public Avisos avisos = new Avisos();
    private SQLException sqlex = new SQLException();
    private Connection db_conexao;
    private ResultSet rsTable;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private final String LINK;
    private static final String USER = "root";
    private static final String PASSWD = "";
    public Conexao(String DANCO) {
        this.LINK = "jdbc:mysql://localhost/"+DANCO;
    };
    public void iniciarConexao(){
        try{
            Class.forName(DRIVER);
            db_conexao = DriverManager.getConnection(LINK, USER, PASSWD);
        } 
        catch(ClassNotFoundException | SQLException ex){
            avisos.erro("Não foi possível iniciar a conexão devido: "+ex.getMessage());
        }
    }

    public Connection getDb_conexao() {
        return db_conexao;
    }

    public ResultSet getRsTable() {
        return rsTable;
    }

    public void setRsTable(String query) {
        try{
            Statement temp = db_conexao.createStatement();
            this.rsTable = temp.executeQuery(query);
        } catch (SQLException ex) {
            avisos.erro("Houve um erro ao executar a busca: " + ex.getMessage());
        }
    }
    
    public void executarQuery(String query){
        try{
            Statement temp = db_conexao.createStatement();
            temp.executeUpdate(query);
            this.sqlex = null;
        } catch (SQLException ex) {
            this.sqlex = ex;
            avisos.erro("Houve um erro ao adiconar dados: " + ex.getMessage());
        }
    }

    public SQLException getSqlex() {
        return sqlex;
    }
    
    public void fecharConexao(){
        try{
            db_conexao.close();
        } catch (SQLException ex) {
            avisos.erro("Houver um erro ao fechar a conexão: " + ex.getMessage());
        }
    }
    
    public boolean proximoItem(){
        try {
            return rsTable.next();
        } catch (SQLException ex) {
            avisos.erro("Não foi possível pegar o proximo item: "+ex.getMessage());
        }
        return false;
    }
    
}
