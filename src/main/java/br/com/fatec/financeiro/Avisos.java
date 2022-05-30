/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.financeiro;

import javafx.scene.control.Alert;

/**
 *
 * @author junior
 */
public class Avisos {
    public Avisos(){}
    
    public void erro(String texto){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText("ERRO!");
        alerta.setContentText(texto);
        alerta.showAndWait(); //exibe a mensagem
    }
    
    public void inform(String texto){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("INFORMAÇÃO!");
        alerta.setHeaderText("INFORMAÇÃO!");
        alerta.setContentText(texto);
        alerta.showAndWait(); //exibe a mensagem
    }
    
    public void ok(String texto){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("FEITO!");
        alerta.setHeaderText("FEITO!");
        alerta.setContentText(texto);
        alerta.showAndWait(); //exibe a mensagem
    }
    
}
