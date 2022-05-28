/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author junior
 */
public class MenuController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Label lbMenu;
    @FXML
    private Button btDepara;
    @FXML
    private Button btUsuarios;
    @FXML
    private Button btValores;
    @FXML
    private Button btVisualizar;
    
    
    private Usuario infoUsuario = new Usuario();
    @FXML
    private Button btGrafico;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void voltar_Click(ActionEvent event) {
        App.mudarCena("Login");
    }

    @FXML
    private void depara_Click(ActionEvent event) {
        App.mudarCena("Depara");
    }

    @FXML
    private void usuarios_Click(ActionEvent event) {
        App.mudarCena("Usuarios");
    }

    @FXML
    private void valores_Cliok(ActionEvent event) {
        App.mudarCena("Grafico");
    }

    @FXML
    private void visualizar_Click(ActionEvent event) {
        App.mudarCena("Visualizar");
    }

    @FXML
    private void grafico_Click(ActionEvent event) {
        App.mudarCena("Grafico");
    }
    
}
