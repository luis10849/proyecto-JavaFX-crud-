package controladores;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.*;

public class FormularioClientesController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField tcod;

	@FXML
	private TextField tnombre;

	@FXML
	private TextField tedad;

	@FXML
	private RadioButton radioFemenino;

	@FXML
	private ToggleGroup GrupoGenero;

	@FXML
	private RadioButton RadioMasculino;

	@FXML
	private DatePicker dateFecha;

	private ObservableList<Cliente> listaClientes;

	@FXML
	private TableView<Cliente> tableClientes;

	@FXML
	private ComboBox<String> cmbPais;

	private ObservableList<String> paises;

	@FXML
	private TableColumn<Cliente, String> clmnnombre;

	@FXML
	private TableColumn<Cliente, Number> clmnedad;

	@FXML
	private TableColumn<Cliente, String> clmngenero;

	@FXML
	private TableColumn<Cliente, String> clmnfechaGrado;

	@FXML
	private TableColumn<Cliente, String> clmnpais;

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnActualizar;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnNuevo;

	private Conexion con;

	@FXML
	void initialize() {
		con = new Conexion();
		con.Conexion();
		assert tcod != null : "fx:id=\"tcod\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert tnombre != null : "fx:id=\"tnombre\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert tedad != null : "fx:id=\"tedad\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert radioFemenino != null : "fx:id=\"radioFemenino\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert GrupoGenero != null : "fx:id=\"GrupoGenero\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert RadioMasculino != null : "fx:id=\"RadioMasculino\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert dateFecha != null : "fx:id=\"dateFecha\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert cmbPais != null : "fx:id=\"cmbPais\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert btnGuardar != null : "fx:id=\"btnGuardar\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert btnActualizar != null : "fx:id=\"btnActualizar\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert btnNuevo != null : "fx:id=\"btnNuevo\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert tableClientes != null : "fx:id=\"tableClientes\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert clmnnombre != null : "fx:id=\"clmnnombre\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert clmnedad != null : "fx:id=\"clmnedad\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert clmngenero != null : "fx:id=\"clmngenero\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert clmnfechaGrado != null : "fx:id=\"clmnfechaGrado\" was not injected: check your FXML file 'FormularioClientes.fxml'.";
		assert clmnpais != null : "fx:id=\"clmnpais\" was not injected: check your FXML file 'FormularioClientes.fxml'.";

		paises = FXCollections.observableArrayList();
		llenarPaises();
		listaClientes = FXCollections.observableArrayList();
		Cliente.llenarInformacionClientes(con.getConnection(), listaClientes);
		tableClientes.setItems(listaClientes);
		clmnnombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		clmnedad.setCellValueFactory(new PropertyValueFactory<Cliente, Number>("edad"));
		clmngenero.setCellValueFactory(new PropertyValueFactory<Cliente, String>("genero"));
		clmnfechaGrado.setCellValueFactory(new PropertyValueFactory<Cliente, String>("fechaGraduacion"));
		clmnpais.setCellValueFactory(new PropertyValueFactory<Cliente, String>("pais"));
		cmbPais.setItems(paises);
		eventoTabla();
		con.cerrarConexion();

	}

	public void eventoTabla() {
		tableClientes.getSelectionModel().selectedItemProperty().addListener(

				new ChangeListener<Cliente>() {

					@Override
					public void changed(ObservableValue<? extends Cliente> observable, Cliente anterior,
							Cliente selecionado) {
						if (selecionado != null) {
							tcod.setText("" + selecionado.getCod());
							tnombre.setText(selecionado.getNombre());
							tedad.setText("" + selecionado.getEdad());
							if (selecionado.getGenero().equals("F")) {
								radioFemenino.setSelected(true);
								RadioMasculino.setSelected(false);
							} else {
								radioFemenino.setSelected(false);
								RadioMasculino.setSelected(true);
							}
							dateFecha.setValue(selecionado.getFechaGraduacion().toLocalDate());
							cmbPais.setValue(selecionado.getPais());
							System.out.println("clik");
							btnGuardar.setDisable(true);
							btnActualizar.setDisable(false);
							btnEliminar.setDisable(false);
						}
					}
				});
	}

	@FXML
	public void guardarRegistro() {
		Cliente c = new Cliente(0, tnombre.getText(), Integer.parseInt(tedad.getText()),
				radioFemenino.isSelected() ? "F" : "M", Date.valueOf(dateFecha.getValue()),
				cmbPais.getSelectionModel().getSelectedItem());
		con.Conexion();
		int r = c.guardarRegistro(con.getConnection());
		con.cerrarConexion();
		if (r == 1) {
			listaClientes.add(c);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Agregado");
			mensaje.setContentText("Registro Agregado Correctamente");
			mensaje.setHeaderText("Resultado :");
			mensaje.showAndWait();
		}
	}

	@FXML
	public void actualizarRegistro() {
		
		if(tableClientes.getSelectionModel().getSelectedIndex()>-1) {
		Cliente c = new Cliente(Integer.parseInt(tcod.getText()), tnombre.getText(), Integer.parseInt(tedad.getText()),
				radioFemenino.isSelected() ? "F" : "M", Date.valueOf(dateFecha.getValue()),
				cmbPais.getSelectionModel().getSelectedItem());
		con.Conexion();
		int r = c.actualizarRegistro(con.getConnection());
		con.cerrarConexion();
		if (r == 1) {
			listaClientes.set(tableClientes.getSelectionModel().getSelectedIndex(), c);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Actualizado");
			mensaje.setContentText("Registro Actualizado Correctamente");
			mensaje.setHeaderText("Resultado :");
			mensaje.showAndWait();
		}
		}
		else {
			Alert mensaje = new Alert(AlertType.WARNING,"por favor selecione un cliente");
			mensaje.showAndWait();
		}
	}

	@FXML
	public void eliminarRegistro() {
		if(tableClientes.getSelectionModel().getSelectedIndex()>-1) {
		Cliente c = tableClientes.getSelectionModel().getSelectedItem();
		con.Conexion();
		int r=c.eliminarRegistro(con.getConnection());
		con.cerrarConexion();
		if (r == 1) {
			listaClientes.remove(tableClientes.getSelectionModel().getSelectedIndex());
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Eliminado");
			mensaje.setContentText("Registro Eliminado Correctamente");
			mensaje.setHeaderText("Resultado :");
			mensaje.showAndWait();
		}
		}
		else {
			Alert mensaje = new Alert(AlertType.WARNING,"por favor selecione un cliente");
			mensaje.showAndWait();
		}

	}

	@FXML
	public void limpiarCampos() {
		tcod.setText(null);
		tnombre.setText(null);
		tedad.setText(null);
		radioFemenino.setSelected(false);
		RadioMasculino.setSelected(false);
		dateFecha.setValue(null);
		cmbPais.setValue(null);
		btnGuardar.setDisable(false);
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
	}

	public void llenarPaises() {
		paises.add("Armenia");
		paises.add("Alemania");
		paises.add("Australia");
		paises.add("Alemania");
		paises.add("Argentina");
		paises.add("Brasil");
		paises.add("Colombia");
		paises.add("China");
		paises.add("Francia");
		paises.add("Japon");
	}
}
