/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Avisos;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Depara;
import br.com.fatec.financeiro.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author junior
 */
public class ValoresController implements Initializable {
    Avisos avisos = new Avisos();
    @FXML
    private Button btVoltar;
    @FXML
    private Button btAdicionar;
    @FXML
    private Button btVisualizar;
    @FXML
    private ComboBox<Depara> cmbFiltro;
    private final Conexao conn = new Conexao("fin");
    ObservableList<Depara> depara_lista = FXCollections.observableArrayList();
    ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Usuario> cmbUsuario;
    @FXML
    private TextField txPreco;
    @FXML
    private DatePicker dtCompra;
    
    private LocalDate data;

    
    
    public void carregarCmbDepara(){
        cmbFiltro.getItems().clear();
        String query = "SELECT * FROM depara_padrao";
        conn.setRsTable(query);
        try{
            while(conn.getRsTable().next()){
                depara_lista.add(new Depara(conn.getRsTable().getInt("id"),
                        conn.getRsTable().getString("tipo"),
                        conn.getRsTable().getInt("depara")));
            }
        } catch (SQLException ex) {
            avisos.erro("Não foi possível obter dados devido: "+ex.getMessage());
        }
        cmbFiltro.setItems(depara_lista);
    }
    
    public void carregarUsuarios(){
        String query = "SELECT * FROM usuarios;";
        conn.setRsTable(query);
        try{
            while(conn.getRsTable().next()){
                usuarios.add(new Usuario(conn.getRsTable().getString("usuario"), 
                        conn.getRsTable().getString("senha"), conn.getRsTable().getInt("id")));
            }
        } catch (SQLException ex) {
            avisos.erro("Não foi possível obter dados devido: "+ex.getMessage());
        }
        cmbUsuario.setItems(usuarios);
    }
    public void adicionarValores(){
        Depara d = cmbFiltro.getValue();
        Usuario u = cmbUsuario.getValue();
        if(this.data == null | txPreco.getText() != "" | cmbUsuario.getSelectionModel().isEmpty() | cmbFiltro.getSelectionModel().isEmpty()){
            String query = "INSERT INTO valores(preco, data_compra, fk_id, fk_depara) "
                    + "VALUES('"+txPreco.getText()+"','"+data.toString()+"','"+u.getId()+"','"+d.getDepara()+"');";
            conn.executarQuery(query);
            txPreco.clear();
            txPreco.requestFocus();
            cmbFiltro.getSelectionModel().clearSelection();
            cmbUsuario.getSelectionModel().clearSelection();
            carregarCmbDepara();
            avisos.ok("Adicionados com sucesso!");
        }else{
            avisos.erro("Não foi possível adicionar banco ao banco de dados!");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn.iniciarConexao();
        carregarCmbDepara();
        carregarUsuarios();
        // TODO
    }    

    @FXML
    private void voltar_Click(ActionEvent event) {
        VisualizarController.newWindow.close();
        App.mudarCena("Visualizar");
    }

    @FXML
    private void adicionar_Click(ActionEvent event) {
        adicionarValores();
        avisos.ok("Adicionado com sucesso!");
    }

    @FXML
    private void visualizar_Click(ActionEvent event) {
        VisualizarController.newWindow.close();
        App.mudarCena("Visualizar");
    }

    @FXML
    private void filtro_Change(ActionEvent event) {
    }

    @FXML
    private void usuario_Change(ActionEvent event) {
    }

    @FXML
    private void date_Click(ActionEvent event) {
        data = dtCompra.getValue();
    }

}
