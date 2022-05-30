/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Avisos;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Depara;
import br.com.fatec.financeiro.Valores;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author junior
 */
public class VisualizarController implements Initializable {

    Avisos avisos = new Avisos();
    
    @FXML
    private Button btVoltar;
    @FXML
    private Button btAdicionar;
    @FXML
    private Button btEditar;
    @FXML
    private Button btRemover;
    @FXML
    private Button btAtualizar;
    @FXML
    private ComboBox<Depara> cmbFiltro;
    @FXML
    private TableView<Valores> tbValores;
    @FXML
    private TableColumn<Valores, Integer> clCompra;
    @FXML
    private TableColumn<Valores, Float> clPreco;
    @FXML
    private TableColumn<Valores, String> clData;
    @FXML
    private TableColumn<Valores, Integer> clUsuario;
    @FXML
    private TableColumn<Valores, Integer> clDepara;
    @FXML
    private TextField txPreco;
    @FXML
    private DatePicker dtEditavel;
    @FXML
    private ComboBox<Depara> cmbDepara;
    public static Stage newWindow = new Stage();
    private final Conexao conn = new Conexao("fin");
    ObservableList<Valores> valores = FXCollections.observableArrayList();
    ObservableList<Depara> depara_lista = FXCollections.observableArrayList();    
    
    LocalDate data;
    
    private void limparSelecoes(){
        txPreco.requestFocus();
        txPreco.clear();
        dtEditavel.setValue(null);
        cmbFiltro.getSelectionModel().clearSelection();
        avisos.ok("Limpo com sucesso!");
    }
    
    
    private void carregarEdicao(){
        tbValores.getSelectionModel().selectedItemProperty().addListener((observado, antigo, novo) -> {
            txPreco.setText(String.valueOf(novo.getPreco()));
        });
    }
    
    
    public void criarJanela() throws IOException{
        newWindow.setTitle("Adicionar Valores");
        //Create view from FXML
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Valores.fxml"));
        //Set view in window
        newWindow.setScene(new Scene(fxmlLoader.load()));
    }
    
    
    public void carregarCmbDepara(){
        String query = "SELECT * FROM depara_padrao";
        conn.setRsTable(query);
        try{
            while(conn.getRsTable().next()){
                depara_lista.add(new Depara(conn.getRsTable().getInt("id"),
                        conn.getRsTable().getString("tipo"),
                        conn.getRsTable().getInt("depara")));
            }
        } catch (SQLException ex) {
            avisos.erro("Não foi possível carregar informações devido: "+ex.getMessage());
        }
        cmbFiltro.setItems(depara_lista);
        cmbDepara.setItems(depara_lista);
    }
    
    
    public void carregarTabelaValores(){
        tbValores.getItems().clear();
        String query = "SELECT * FROM valores;";
        conn.setRsTable(query);
        try{
            while(conn.getRsTable().next()){
                valores.add(new Valores(conn.getRsTable().getInt("id"), 
                        conn.getRsTable().getBigDecimal("preco"), 
                        conn.getRsTable().getDate("data_compra"), 
                        conn.getRsTable().getInt("fk_id"), 
                        conn.getRsTable().getInt("fk_depara")));
                }
            } catch (SQLException ex) {
                avisos.erro("Não foi possível carregar informações devido: "+ex.getMessage());
            }
        tbValores.setItems(valores);
    }
    
    public void atualizarFiltro(int fil){
        tbValores.getItems().clear();
        String query = "SELECT * FROM valores WHERE fk_depara = "+fil+";";
            conn.setRsTable(query);
            try{
                while(conn.getRsTable().next()){
                    valores.add(new Valores(conn.getRsTable().getInt("id"), 
                            conn.getRsTable().getBigDecimal("preco"), 
                            conn.getRsTable().getDate("data_compra"), 
                            conn.getRsTable().getInt("fk_id"), 
                            conn.getRsTable().getInt("fk_depara")));
                }
            } catch (SQLException ex) {
                avisos.erro("Não foi possível carregar informações devido: "+ex.getMessage());
            }
            tbValores.setItems(valores);
        };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clCompra.setCellValueFactory(new PropertyValueFactory<>("id"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        clData.setCellValueFactory(new PropertyValueFactory<>("data"));
        clUsuario.setCellValueFactory(new PropertyValueFactory<>("fk_id"));
        clDepara.setCellValueFactory(new PropertyValueFactory<>("fk_depara"));
        conn.iniciarConexao();
        carregarTabelaValores();
        carregarCmbDepara();
        atualizarTabela();
        carregarFiltro();
        try {
            criarJanela();
        } catch (IOException ex) {
            avisos.erro("Não foi possível instanciar uma janela devido: "+ex.getMessage());
        }
    }   
    
    private void atualizarTabela(){
        tbValores.getSelectionModel().selectedItemProperty().addListener((observado, velho, novo) -> {
            if(novo != null){
                txPreco.setText(String.valueOf(novo.getPreco()));
            }
        });
    }

    private void carregarFiltro(){
        cmbFiltro.valueProperty().addListener((observado, velho, novo) -> {
            Depara p = new Depara();
            p = cmbFiltro.getValue();
            //atualizarFiltro(p.getDepara());
        });
    }
    @FXML
    private void voltar_Click(ActionEvent event) {
        App.mudarCena("Menu");
    }
    @FXML
    private void adicionar_Click(ActionEvent event) throws IOException {
        //Launch
        newWindow.show();
    }

    @FXML
    private void editar_Click(ActionEvent event) {
        Depara aux = cmbDepara.getValue();
        Valores val = tbValores.getSelectionModel().getSelectedItem();
        if(txPreco.getText() != "" | dtEditavel.getValue() != null){
            String query = "UPDATE valores"
                    + " SET preco = "+txPreco.getText()+", data_compra = '"+data.toString()+"', fk_depara = "+aux.getDepara()+""
                    + " WHERE id = " + val.getId() + ";";
            conn.executarQuery(query);
            limparSelecoes();
            avisos.ok("Adicionado com sucesso!");
        }else{
            avisos.erro("Não foi possível editar dados");
        }
        cmbDepara.getSelectionModel().clearSelection();
    }

    @FXML
    private void remover_Click(ActionEvent event) {
        Valores aux = tbValores.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM valores WHERE id = "+aux.getId()+";";
        conn.executarQuery(query);
        carregarTabelaValores();
        cmbFiltro.getSelectionModel().clearSelection();
    }

    @FXML
    private void atualizar_Click(ActionEvent event) {
        carregarTabelaValores();
        cmbFiltro.getSelectionModel().clearSelection();
    }

    @FXML
    private void filtro_Change(ActionEvent event) {
        System.out.println(cmbFiltro.getSelectionModel().getSelectedItem().getDepara());
        atualizarFiltro(cmbFiltro.getSelectionModel().getSelectedItem().getDepara());
    }


    @FXML
    private void dataEditavel(ActionEvent event) {
        data = dtEditavel.getValue();
    }

    @FXML
    private void depara_Change(ActionEvent event) {
        
    }

    @FXML
    private void filtro_Drop(DragEvent event) {
    }

    
}
