/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Avisos;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Depara;
import br.com.fatec.financeiro.ListaCompras;
import com.opencsv.CSVWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Tiago
 */
public class ListaComprasController implements Initializable {

    Avisos avisos = new Avisos();

    @FXML   
    private TableColumn<ListaCompras, Integer> clId;
    @FXML   
    private TableColumn<ListaCompras, Integer> clPreco_compra;
    @FXML   
    private TableColumn<ListaCompras, String> clData_compra;
    @FXML  
    private TableColumn<ListaCompras, String> clCategoria;
    @FXML  
    private Button btnInserir;
    @FXML
    private Button btnRemover;
    @FXML
    private TableView<ListaCompras> tabelaLista;
    @FXML
    private Label lblPreco_compra;
    @FXML
    private Label lblData_compra;
    @FXML
    private Label lblCategoria;
    @FXML
    private TextField txtPreco_compra;
    private TextField txtData_compra;
    @FXML
    private ComboBox<Depara> cbCategoria;
    
    ObservableList<ListaCompras> lista = FXCollections.observableArrayList();
    ObservableList<Depara> combo = FXCollections.observableArrayList();
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnExportar;
    @FXML
    private DatePicker dtCompra;
    
    
    LocalDate data = null;
    @FXML
    private Button btVoltar;
    Conexao conn = new Conexao("fin");
    /**
     * Initializes the controller class.
     */
    
    public void carregarCmbDepara(){
        String query = "SELECT * FROM depara_padrao";
        conn.setRsTable(query);
        try{
            while(conn.getRsTable().next()){
                combo.add(new Depara(conn.getRsTable().getInt("id"),
                        conn.getRsTable().getString("tipo"),
                        conn.getRsTable().getInt("depara")));
            }
        } catch (SQLException ex) {
            avisos.erro("Não foi possível carregar os dados devido: "+ex.getMessage());
        }
        cbCategoria.setItems(combo);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        conn.iniciarConexao();
        //Configurando o comportamento das celulas
        tabelaLista.getSelectionModel().selectedItemProperty().addListener((observado, velho, novo) -> {
            if(novo!= null){
                txtPreco_compra.setText(Integer.toString(novo.getPrecoCompra()));
                atualizarLista();
            }
        });
        clId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        clPreco_compra.setCellValueFactory(new PropertyValueFactory<>("precoCompra"));
        clData_compra.setCellValueFactory(new PropertyValueFactory< >("dataCompra"));
        clCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        carregarCmbDepara();
        atualizarLista();
 
    }   
    
    
    private void atualizarLista(){
        tabelaLista.setItems(lista);
    }
    
    // popula combo box 
    
    
    private void limpar(){
        txtPreco_compra.setText(null);
        cbCategoria.getSelectionModel().clearSelection();
    }
    
    // Botão inserir

    @FXML
    private void inserir(ActionEvent event) {
        if(txtPreco_compra.getText() != "" && cbCategoria.getSelectionModel().getSelectedItem() != null){
            int id = 0;
            ListaCompras aux = new ListaCompras();
            if(lista != null){
                for(int i = 0; i < lista.size(); i++){
                    aux = lista.get(i);
                }
            }
            lista.add(new ListaCompras((aux.getID()+1),
                    parseInt(txtPreco_compra.getText()),
                    String.valueOf(data),
                    cbCategoria.getSelectionModel().getSelectedItem().getTipo()));
            tabelaLista.setItems(lista);
            avisos.ok("Adicionado com sucesso!");
            atualizarLista();
        }else{
            avisos.erro("Não foi possível inserir dados! Campos vazios");
        }
        limpar();
       //pega todos os valores e transforma em uma lista
       
    }
    
    //Remove uma lista selecionada
    
    @FXML
    private void remove(ActionEvent event) {
        if(tabelaLista.getSelectionModel().isEmpty() == true){
            avisos.erro("Nada selecionado!");
        }
        else{
            int selectedID = tabelaLista.getSelectionModel().getSelectedIndex();
            tabelaLista.getItems().remove(selectedID);
            avisos.ok("Removido com sucesso!");
        }  
    }
    
    // atualiza com informações novas 

    @FXML
    private void btnAtualizar_clicked(ActionEvent event) {
        ListaCompras aux = tabelaLista.getSelectionModel().getSelectedItem();
        if(txtPreco_compra.getText() != "" && cbCategoria.getSelectionModel().getSelectedItem() != null){
            for(ListaCompras l: lista){
                if(aux.getID() == l.getID()){
                    l.setCategoria(cbCategoria.getSelectionModel().getSelectedItem().getTipo());
                    l.setDataCompra(String.valueOf(data));
                    l.setPrecoCompra(parseInt(txtPreco_compra.getText()));
                    tabelaLista.setItems(lista);
                }
            }
            avisos.ok("Atualizados com sucesso!");
        }
        else{
            avisos.erro("Não foi possível inserir dados! Campos vazios");
        }
        tabelaLista.setItems(lista);
    }

    @FXML
    private void btnExportar_clicked(ActionEvent event) throws IOException {
        if(lista != null){
            String[] joaoGomes = {"Id", "Preco", "Data", "Categoria"};
            ArrayList<String[]> meuMel = new ArrayList();
            for(ListaCompras l: lista){
                meuMel.add(new String[]{String.valueOf(l.getID()), String.valueOf(l.getPrecoCompra()), String.valueOf(l.getDataCompra()), String.valueOf(l.getCategoria())});
            }
            Writer coracaoBandido = Files.newBufferedWriter(Paths.get("pessoas.csv"));
            CSVWriter csv = new CSVWriter(coracaoBandido, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            csv.writeNext(joaoGomes);
            csv.writeAll(meuMel);
            csv.flush();
            coracaoBandido.close();
            avisos.ok("Dados exportados com sucesso!");
        }else{
            avisos.erro("Não foi possível exportar os dados!");
        }
        
    }

    @FXML
    private void dtCompra_Click(ActionEvent event) {
        data = dtCompra.getValue();
    }

    @FXML
    private void voltar_Click(ActionEvent event) {
        App.mudarCena("Menu");
    }
}
    
    

   