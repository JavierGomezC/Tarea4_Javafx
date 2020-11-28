/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import database.JdbcHelper;
import entidades.Canal;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class canalRepo {
    
    public boolean insertar(Canal canal){
        String query = "INSERT INTO canal (numero, nombre, categoria, calidad) VALUES ("+canal.getNumero()+",'"+canal.getNombre()+"','"+canal.getCategoria()+"','"+canal.getCalidad()+"')";
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean modificar(Canal canal){
        String query = "UPDATE canal SET numero="+canal.getNumero()+", nombre='"+canal.getNombre()+"', categoria='"+canal.getCategoria()+"', calidad='"+canal.getCalidad()+"' WHERE id="+canal.getId();
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean eliminar(int id){
        String query = "DELETE FROM canal WHERE id="+id;
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean validar(Canal canal){
        StringBuilder sb = new StringBuilder();
        boolean esValido = true;
        System.out.println(canal);
        if(canal == null){
            esValido = false;
            sb.append("No existe canal\n");
        }
        if(canal.getNumero() <= 0){
            esValido = false;
            sb.append("Numero requerido\n");
        }
        if(canal.getNombre().trim().equals("")){
            esValido = false;
            sb.append("Nombre requerido\n");
        }
        if(canal.getCategoria().trim().equals("")){
            esValido = false;
            sb.append("Categoria requerido\n");
        }
        if(canal.getCalidad().trim().equals("")){
            esValido = false;
            sb.append("Calidad requerido\n");
        }
        
        if(!esValido){
            JOptionPane.showMessageDialog(null, "Se encontraron los siguientes errores: \n"+sb.toString(), "Error de validacion", JOptionPane.WARNING_MESSAGE);
        }
        return esValido;
    }
    
    public boolean guardar(Canal canal){
        if(validar(canal) == false){
            return false;
        }
        boolean exito;
        if(canal.getId() == 0)
            exito = insertar(canal);
        else
            exito = modificar(canal);
        return exito;
    }
    
    public Canal buscarCanal(int idC){
        String query = "SELECT * FROM canal WHERE id="+idC;
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
        
        Canal canal = null;
        try {
            if(rs.next()){
                int id = idC;
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String calidad = rs.getString("calidad");
                canal = new Canal(id, id, nombre, categoria, calidad);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Canal: \n"+e, "Error de validacion", JOptionPane.WARNING_MESSAGE);
        }
        return canal;
    }
    
    public ObservableList<Canal> buscarTodos(){
        String query = "SELECT * FROM canal";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
    
        ObservableList<Canal> canales = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String calidad = rs.getString("calidad");
                canales.add(new Canal(id, Integer.parseInt(numero), nombre, categoria, calidad));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Canales: \n"+e, "Error de validacion", JOptionPane.WARNING_MESSAGE);
        }
        return canales;
    }
    
    public ObservableList<Canal> buscarPorNumero(String numeroBus){
        String query = "SELECT * FROM canal WHERE numero LIKE '%"+numeroBus+"%'";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
    
        ObservableList<Canal> canales = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String calidad = rs.getString("calidad");
                canales.add(new Canal(id, id, nombre, categoria, calidad));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Canales: \n"+e, "Error de validacion", JOptionPane.WARNING_MESSAGE);
        }
        return canales;
    }
    
    public ObservableList<Canal> buscarPorNombre(String nombreBus){
        String query = "SELECT * FROM canal WHERE nombre LIKE '%"+nombreBus+"%'";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
    
        ObservableList<Canal> canales = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String calidad = rs.getString("calidad");
                canales.add(new Canal(id, id, nombre, categoria, calidad));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Canales: \n"+e, "Error de validacion", JOptionPane.WARNING_MESSAGE);
        }
        return canales;
    }
    
    public ObservableList<Canal> buscarPorCategoria(String categoriaBus){
        String query = "SELECT * FROM canal WHERE categoria LIKE '%"+categoriaBus+"%'";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
    
        ObservableList<Canal> canales = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String calidad = rs.getString("calidad");
                canales.add(new Canal(id, id, nombre, categoria, calidad));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Canales: \n"+e, "Error de validacion", JOptionPane.WARNING_MESSAGE);
        }
        return canales;
    }
    
    public ObservableList<Canal> buscarPorCalidad(String calidadBus){
        String query = "SELECT * FROM canal WHERE calidad LIKE '%"+calidadBus+"%'";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
    
        ObservableList<Canal> canales = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String calidad = rs.getString("calidad");
                canales.add(new Canal(id, id, nombre, categoria, calidad));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Canales: \n"+e, "Error de validacion", JOptionPane.WARNING_MESSAGE);
        }
        return canales;
    }
}
