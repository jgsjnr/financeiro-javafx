/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.financeiro.Controllers;

import br.com.fatec.financeiro.App;
import br.com.fatec.financeiro.Avisos;
import br.com.fatec.financeiro.Conexao;
import br.com.fatec.financeiro.Dados;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    Avisos avisos = new Avisos();
    Conexao conn = new Conexao("fin");
    ArrayList<Dados> dados = new ArrayList<>();
    XYChart.Series serieLn = new XYChart.Series();
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btVoltar;
    @FXML
    private BarChart<String, Integer> gfGastos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn.iniciarConexao();
        carregarDados();
        //transferirDados();        
        executarDados();
    }
    
    private void carregarDados(){
        String query = "select sum(preco) as soma, year(data_compra) as ano from valores group by ano;";
        conn.setRsTable(query);
        try {
            while(conn.getRsTable().next()){
                serieLn.getData().add(new XYChart.Data(conn.getRsTable().getString("ano"), conn.getRsTable().getInt("soma")));
            }
        } catch (SQLException ex) {
            avisos.erro("Não foi possível carregar dados devido: "+ex.getMessage());
        }
    }
    /*
    private void transferirDados(){
        serieLn.getData().removeAll();
        for(Dados d: dados){
            serieLn.getData().add(new XYChart.Data(d.getAno(), d.getValor()));
        }
    }
    */
    private void executarDados(){
        gfGastos.getData().addAll(serieLn);
    }

    @FXML
    private void atualizar_Click(ActionEvent event) {
        //transferirDados();
        carregarDados();
        avisos.ok("Dados atualizados com sucesso!");
    }

    @FXML
    private void voltar_Click(ActionEvent event) {
        App.mudarCena("Menu");
    }
    
}
