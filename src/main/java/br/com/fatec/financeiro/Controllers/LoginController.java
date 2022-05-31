/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Avisos;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author junior
 */
public class LoginController implements Initializable {

    Avisos avisos = new Avisos();
    
    @FXML
    private Button btLogin;
    @FXML
    private Label lbLogin;
    @FXML
    private Label lbSenha;
    @FXML
    private TextField txUsuario;
    @FXML
    private TextField txSenha;
    private Usuario infoUsuario;
    private String usuario_a;
    private String senha_a;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btLogin(ActionEvent event) {
        String usuario = txUsuario.getText();
        String senha = txSenha.getText();
        Conexao login = new Conexao("fin");
        login.iniciarConexao();
        login.setRsTable("SELECT * FROM usuarios WHERE usuario = '" + usuario + "';");
        try{
            while(login.getRsTable().next()){
                usuario_a = login.getRsTable().getString("usuario");
                senha_a = login.getRsTable().getString("senha");
            }
        } catch (SQLException ex) {
            avisos.erro("Não foi possível obter os dados devido: "+ex.getMessage());
        }
        if(usuario.equals(usuario_a) && senha.equals(senha_a)){
            infoUsuario = new Usuario(usuario_a, senha_a);
            System.out.println(infoUsuario.getUsuario());
            avisos.ok("Acesso permitido, seja bem vindo!");
            App.mudarCena("Menu");
        }
        else{
            avisos.erro("Não foi possível realizar login!");
        }
    }
    
}
