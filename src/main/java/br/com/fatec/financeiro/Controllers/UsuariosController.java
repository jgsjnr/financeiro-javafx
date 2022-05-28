/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Usuario;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author junior
 */
public class UsuariosController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Button btAdicionar;
    @FXML
    private Button btRemover;
    @FXML
    private TextField txUsuario;
    @FXML
    private TextField txSenha;
    ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    ObservableList<String> nivel = FXCollections.observableArrayList();
    Conexao conn = new Conexao("fin");
    @FXML
    private TableView<Usuario> tbUsuarios;
    @FXML
    private TableColumn<Usuario, String> clUsuario;
    @FXML
    private TableColumn<Usuario, String> clSenha;
   
    
    public void carregarUsuarios(){
        tbUsuarios.getItems().clear();
        clUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        clSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        String query = "SELECT * FROM usuarios;";
        conn.setRsTable(query);
        try{
            while(conn.getRsTable().next()){
                usuarios.add(new Usuario(conn.getRsTable().getString("usuario"), 
                        conn.getRsTable().getString("senha")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbUsuarios.setItems(usuarios);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn.iniciarConexao();
        tbUsuariosChange();
        carregarUsuarios();
        // TODO
    }    

    @FXML
    private void voltar_Click(ActionEvent event) {
        App.mudarCena("Menu");
    }

    @FXML
    private void adicionar_Click(ActionEvent event) {
        if(txUsuario.getText() != "" | txSenha.getText() != "" ){
            String query = "INSERT INTO usuarios(usuario, senha) values('"+txUsuario.getText()+"','"+txSenha.getText()+"');";
            conn.executarQuery(query);
            txUsuario.clear();
            txSenha.clear();
            carregarUsuarios();
        }else{
            Alert alerta_b = new Alert(Alert.AlertType.INFORMATION);
            alerta_b.setTitle("Mensagem");
            alerta_b.setHeaderText("Informações");
            alerta_b.setContentText("Não é posssível adicionar valores vazios");
            alerta_b.showAndWait(); //exibe a mensagem
        }
    }

    @FXML
    private void remover_Click(ActionEvent event) {
        Usuario pos = tbUsuarios.getSelectionModel().getSelectedItem();
        if(txUsuario.getText() != "" | txSenha.getText() != ""){
            String query = "DELETE FROM usuarios WHERE usuario = '"+txUsuario.getText()+"';";
            conn.executarQuery(query);
            txUsuario.clear();
            txSenha.clear();
        }else{
            Alert alerta_b = new Alert(Alert.AlertType.INFORMATION);
            alerta_b.setTitle("Mensagem");
            alerta_b.setHeaderText("Informações");
            alerta_b.setContentText("Não é posssível remover valores devido");
            alerta_b.showAndWait(); //exibe a mensagem
        }
        carregarUsuarios();
    }
    
    private void tbUsuariosChange(){
        tbUsuarios.getSelectionModel().selectedItemProperty().addListener((observado, velho, novo) -> {
            if (novo != null) {
                txUsuario.setText(novo.getUsuario());
                txSenha.setText(novo.getSenha());
            }
        });
    }


    
}
