/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Dados;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author junior
 */
public class GraficoController implements Initializable {
    Conexao conn = new Conexao("fin");
    ArrayList<Dados> dados = new ArrayList<>();
    XYChart.Series serieBar = new XYChart.Series();
    XYChart.Series serieLn = new XYChart.Series();
    @FXML
    private BarChart<String, Integer> gfGastos;
    @FXML
    private Button btnAtualizar;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private LineChart<String, Integer> gfGastoln;
    @FXML
    private NumberAxis yln;
    @FXML
    private CategoryAxis xln;
    @FXML
    private Button btVoltar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn.iniciarConexao();
        carregarDados();
        carregarDados();
        transferirDados();        
        executarDadosLn();
        executarDadosBar();

    }
    
    private void carregarDados(){
        String query = "select sum(preco) as soma, year(data_compra) as ano from valores group by ano;";
        conn.setRsTable(query);
        try {
            while(conn.getRsTable().next()){
                dados.add(new Dados(conn.getRsTable().getString("ano"), conn.getRsTable().getFloat("soma")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraficoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void transferirDados(){
        for(Dados d: dados){
            serieLn.getData().add(new XYChart.Data(d.getAno(), d.getValor()));
            serieBar.getData().add(new XYChart.Data(d.getAno(), d.getValor()));
        }
    }
    
    private void executarDadosLn(){
        gfGastoln.getData().addAll(serieLn);
    }
    private void executarDadosBar(){
        gfGastos.getData().addAll(serieBar);
    }

    @FXML
    private void atualizar_Click(ActionEvent event) {
        carregarDados();
        transferirDados();
        executarDadosBar();
        executarDadosLn();
    }

    @FXML
    private void voltar_Click(ActionEvent event) {
        App.mudarCena("Menu");
    }
    
}
