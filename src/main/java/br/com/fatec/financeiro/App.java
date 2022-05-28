package br.com.fatec.financeiro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    private static Scene menu;
    private static Scene visualizar;
    private static Scene usuarios;
    private static Scene valores;
    private static Scene depara;
    private static Scene grafico;
    private static Scene listaCompras;
    private static Stage stage;

    
    private void colocarNomes(){
    }
    @Override
    public void start(Stage stage) throws IOException {
        visualizar = new Scene(loadFXML("Visualizar"), 857, 484);
        usuarios = new Scene(loadFXML("Usuarios"), 751, 200);
        valores = new Scene(loadFXML("Valores"), 600, 200);
        depara = new Scene(loadFXML("Depara"), 774, 227);
        menu = new Scene(loadFXML("Menu"), 600, 400);
        grafico = new Scene(loadFXML("Grafico"), 601, 549);
        scene = new Scene(loadFXML("Login"), 453, 176);
        listaCompras = new Scene(loadFXML("listaCompras"), 607, 400);
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Sistema financeiro pessoal");
        App.stage = stage;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        visualizar.setRoot(loadFXML(fxml));
        usuarios.setRoot(loadFXML(fxml));
        valores.setRoot(loadFXML(fxml));
        depara.setRoot(loadFXML(fxml));
        menu.setRoot(loadFXML(fxml));
        grafico.setRoot(loadFXML(fxml));
        listaCompras.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    
    public static void mudarCena(String cena){
        switch(cena){
            case "Menu":
                stage.setScene(menu);
                stage.setTitle("Sistema financeiro pessoal: Menu");
                break;
            case "Login":
                stage.setScene(scene);
                stage.setTitle("Sistema financeiro pessoal: Login");
                break;
            case "Visualizar":
                stage.setScene(visualizar);
                stage.setTitle("Sistema financeiro pessoal: Visualizar");
                break;
            case "Valores":
                stage.setScene(valores);
                stage.setTitle("Sistema financeiro pessoal: Adicionar Valores");
                break;
            case "Usuarios":
                stage.setScene(usuarios);
                stage.setTitle("Sistema financeiro pessoal: Manutenção de usuarios");
                break;
            case "Depara":
                stage.setScene(depara);
                stage.setTitle("Sistema financeiro pessoal: Manutenção das cateogorias");
                break;
            case "Grafico":
                stage.setScene(grafico);
                stage.setTitle("Sistema financeiro pessoal: Graficos");
                break;
            case "ListaCompras":
                stage.setScene(listaCompras);
                stage.setTitle("Sistema financeiro pessoal: Graficos");
                break;
        }
    }

}