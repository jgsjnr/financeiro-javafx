/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Depara;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author junior
 */
public class DeparaController implements Initializable {
    
    

    @FXML
    private Button btVoltar;
    @FXML
    private TableView<Depara> tbDepara;
    @FXML
    private TableColumn<Depara, Integer> idDepara;
    @FXML
    private TableColumn<Depara, Integer> nmDepara;
    @FXML
    private TableColumn<Depara, String> tpDepara;
    
    ObservableList<Depara> depara = FXCollections.observableArrayList();
    
    Conexao conn = new Conexao("fin");
    @FXML
    private Button btnRemover;
    @FXML
    private Button btAdicionar;
    @FXML
    private TextField txDepara;
    @FXML
    private TextField txTipo;
    
    public void initDepara(){
        tbDepara.getItems().clear();
        idDepara.setCellValueFactory(new PropertyValueFactory<>("id"));
        nmDepara.setCellValueFactory(new PropertyValueFactory<>("depara"));
        tpDepara.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        String query = "SELECT * FROM depara_padrao;";
        conn.setRsTable(query);
        try {
            while(conn.getRsTable().next()){
                depara.add(new Depara(conn.getRsTable().getInt("id"),
                        conn.getRsTable().getString("tipo"),
                        conn.getRsTable().getInt("depara")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeparaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbDepara.setItems(depara);
    }
    public void deletarDepara(){
        Depara pos = tbDepara.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM depara_padrao WHERE depara = "+pos.getDepara()+";";
        conn.executarQuery(query);
        System.out.println(pos.getTipo());
    }
    
    public void inserirDepara(){
        if(txDepara.getText() != "" | txTipo.getText() != ""){
            String query = "INSERT INTO depara_padrao(depara, tipo) VALUES('"+txDepara.getText()+"','"+txTipo.getText()+"');";
            conn.executarQuery(query);
        }else{
            System.out.println("Campos vazios");
        }
        initDepara();
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn.iniciarConexao();
        initDepara();
        // TODO
    }    

    @FXML
    private void voltar_Click(ActionEvent event) {
        App.mudarCena("Menu");
    }

    @FXML
    private void remover_Click(ActionEvent event) {
        deletarDepara();
        initDepara();
    }

    @FXML
    private void adicionar_Click(ActionEvent event) {
        inserirDepara();
        initDepara();
    }
    
}