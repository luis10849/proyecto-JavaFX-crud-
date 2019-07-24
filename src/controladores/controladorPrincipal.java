package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class controladorPrincipal {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuInformacion;

    @FXML
    private MenuItem itemClientes;

    @FXML
    private MenuItem itemProductos;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void mostrarInformacionClientes(ActionEvent event) throws IOException {
            AnchorPane a= (AnchorPane) FXMLLoader.load(getClass().getResource("/vistas/FormularioClientes.fxml"));
            anchorPane.getChildren().setAll(a);
            
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert menuInformacion != null : "fx:id=\"menuInformacion\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert itemClientes != null : "fx:id=\"itemClientes\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert itemProductos != null : "fx:id=\"itemProductos\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        

    }
}

