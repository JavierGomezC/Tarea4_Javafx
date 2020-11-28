/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.canales;

import entidades.Canal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javax.swing.JOptionPane;
import repositorio.canalRepo;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CanalController implements Initializable {

    public ObservableList<Canal> dataCanal = FXCollections.observableArrayList();
    @FXML
    private Tab tabEntrada;
    @FXML
    private TextField textId;
    @FXML
    private TextField textNumero;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textCategoria;
    @FXML
    private TextField textCalidad;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGuardo;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Tab tabSalida;
    @FXML
    private TextField textBuscar;
    @FXML
    private ChoiceBox<String> choiceBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TableView<Canal> tablaCanal;
    @FXML
    private TableColumn<Canal, Integer> colId;
    @FXML
    private TableColumn<Canal, String> colNumero;
    @FXML
    private TableColumn<Canal, String> colNombre;
    @FXML
    private TableColumn<Canal, String> colCategoria;
    @FXML
    private TableColumn<Canal, String> colCalidad;
    @FXML
    private Label labResultado;
    @FXML
    private TabPane tabPanelCanal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        rellenarTablaLibro();
        vaciarCampos();
        deshabiliarCampos();
        
        choiceBuscar.getItems().addAll("Id", "Numero", "Nombre", "Categoria", "Calidad");
        choiceBuscar.setValue("Id");
    }    
    
    public void configurarTabla(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colCalidad.setCellValueFactory(new PropertyValueFactory<>("calidad"));
        tablaCanal.setItems(dataCanal);
    }

    public void rellenarTablaLibro() {
        dataCanal.clear();
        canalRepo canalRepo = new canalRepo();
        ObservableList<Canal> canales = canalRepo.buscarTodos();
        dataCanal.setAll(canales);
        int resultados = canales.size();
        labResultado.setText("Resultados: "+resultados);
    }
    
    private void vaciarCampos(){
        textId.setText("");
        textNumero.setText("");
        textNombre.setText("");
        textCategoria.setText("");
        textCalidad.setText("");
    }
    
    private void deshabiliarCampos(){
        textId.setDisable(true);
        textNumero.setDisable(true);
        textNombre.setDisable(true);
        textCategoria.setDisable(true);
        textCalidad.setDisable(true);
    }
    
    private void habiliarCampos(){
        textId.setDisable(true);
        textNumero.setDisable(false);
        textNombre.setDisable(false);
        textCategoria.setDisable(false);
        textCalidad.setDisable(false);
    }
    
    @FXML
    private void btnNuevo_click(ActionEvent event) {
        vaciarCampos();
        habiliarCampos();
        btnNuevo.setDisable(true);
        btnGuardo.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
    }

    @FXML
    private void btnGuardo_click(ActionEvent event) {
        int id;
        int numero = Integer.parseInt(textNumero.getText());
        String nombre = textNombre.getText();
        String categoria = textCategoria.getText();
        String calidad = textCalidad.getText();
        
        Canal canal;
        if(textId.getText().trim().equals(""))
            canal = new Canal(numero, nombre, categoria, calidad);
        else
            canal = new Canal(Integer.parseInt(textId.getText()), numero, nombre, categoria, calidad);
        canalRepo canalRepo = new canalRepo();
        if(canalRepo.guardar(canal)){
            vaciarCampos();
            deshabiliarCampos();
            btnNuevo.setDisable(false);
            btnGuardo.setDisable(true);
            btnEliminar.setDisable(true);
            btnCancelar.setDisable(true);
            
            JOptionPane.showMessageDialog(null, "Se han guardado los cambios.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaLibro();
        }
    }

    @FXML
    private void btnEliminar_click(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null, "Desea eliminar el registro?",
                "Confirmacion", JOptionPane.YES_NO_OPTION,2);
        if(opcion == JOptionPane.YES_NO_OPTION){
            int id = Integer.parseInt(textId.getText());
            canalRepo canalRepo = new canalRepo();
            canalRepo.eliminar(id);
            JOptionPane.showMessageDialog(null, "Registro eliminado con exito.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            vaciarCampos();
            deshabiliarCampos();
            btnNuevo.setDisable(false);
            btnGuardo.setDisable(true);
            btnEliminar.setDisable(true);
            btnCancelar.setDisable(true);
            rellenarTablaLibro();
        }
    }

    @FXML
    private void btnCancelar_click(ActionEvent event) {
        vaciarCampos();
        deshabiliarCampos();
        btnNuevo.setDisable(false);
        btnGuardo.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    @FXML
    private void btnBuscar_click(ActionEvent event) {
        dataCanal.clear();
        String modoBusqueda = choiceBuscar.getValue();
        if(modoBusqueda.equals("Id")){
            int id = 0;
            try {
                id = Integer.parseInt(textId.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese un Id Valido", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            canalRepo canalRepo = new canalRepo();
            Canal canal = canalRepo.buscarCanal(id);
            if(canal != null){
                dataCanal.add(canal);
                labResultado.setText("Resultados: 1");
            }else{
                labResultado.setText("Resultados: 0");
            }
            return;
        }
        
        canalRepo canalRepo = new canalRepo();
        ObservableList<Canal> canales = FXCollections.observableArrayList();
        
        switch(modoBusqueda){
            case "Numero":
                String busquedaNumero = textBuscar.getText();
                canales = canalRepo.buscarPorNumero(busquedaNumero);
                break;
            case "Nombre":
                String busquedaNombre = textBuscar.getText();
                canales = canalRepo.buscarPorNombre(busquedaNombre);
                break;
            case "Categoria":
                String busquedaCategoria = textBuscar.getText();
                canales = canalRepo.buscarPorCategoria(busquedaCategoria);
                break;
            case "Calidad":
                String busquedaCalidad = textBuscar.getText();
                canales = canalRepo.buscarPorCalidad(busquedaCalidad);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Modo de Busqueda Incorrecto", "Error", JOptionPane.WARNING_MESSAGE);
                return;
        }
        
        dataCanal.setAll(canales);
        int resultados = canales.size();
        labResultado.setText("Resultados: "+resultados);
    }

    @FXML
    private void btnRefrescar_click(ActionEvent event) {
        rellenarTablaLibro();
    }

    @FXML
    private void contextMenu_click(ContextMenuEvent event) {
        ContextMenu menu = new ContextMenu();
        MenuItem itemModificar = new MenuItem("Modificar");
        itemModificar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Canal canal = tablaCanal.getSelectionModel().getSelectedItem();
                if(canal == null){
                    JOptionPane.showMessageDialog(null, "Seleccione un canal para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                textId.setText(String.valueOf(canal.getId()));
                textNumero.setText(String.valueOf(canal.getNumero()));
                textNombre.setText(String.valueOf(canal.getNombre()));
                textCategoria.setText(String.valueOf(canal.getCategoria()));
                textCalidad.setText(String.valueOf(canal.getCalidad()));
                
                habiliarCampos();
                btnNuevo.setDisable(true);
                btnGuardo.setDisable(false);
                btnEliminar.setDisable(false);
                btnCancelar.setDisable(false);
                
                SingleSelectionModel<Tab> selectionModel = tabPanelCanal.getSelectionModel();
                selectionModel.select(0);
            }
        });
        
        menu.getItems().add(itemModificar);
        tablaCanal.setContextMenu(menu);
    }
    
}
