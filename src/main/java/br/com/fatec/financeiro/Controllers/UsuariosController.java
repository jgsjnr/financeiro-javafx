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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    Avisos avisos = new Avisos();
   
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
            avisos.erro("Não foi possível obter os dados!"+ex.getMessage());
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
            avisos.ok("Adicionado com sucesso!");
        }else{
            avisos.erro("Não foi possível adicionar");
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
            avisos.ok("Removido com sucesso!");
        }else{
            avisos.erro("Não foi possível remover");
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
