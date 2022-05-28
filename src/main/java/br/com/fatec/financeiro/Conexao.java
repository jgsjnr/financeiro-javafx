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
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensagem");
            alerta.setHeaderText("Informações");
            alerta.setContentText("Erro: " + ex.getMessage());
            alerta.showAndWait(); //exibe a mensagem
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
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensagem");
            alerta.setHeaderText("Informações");
            alerta.setContentText("Houve um erro ao executar a busca: " + ex.getMessage());
            alerta.showAndWait(); //exibe a mensagem
        }
    }
    
    public void executarQuery(String query){
        try{
            Statement temp = db_conexao.createStatement();
            temp.executeUpdate(query);
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensagem");
            alerta.setHeaderText("Informações");
            alerta.setContentText("Houve um erro ao adiconar dados: " + ex.getMessage());
            alerta.showAndWait(); //exibe a mensagem
        }
    }
    
    public void fecharConexao(){
        try{
            db_conexao.close();
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensagem");
            alerta.setHeaderText("Informações");
            alerta.setContentText("Houver um erro ao fechar a conexão: " + ex.getMessage());
            alerta.showAndWait(); //exibe a mensagem
        }
    }
    
    public boolean proximoItem(){
        try {
            return rsTable.next();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
