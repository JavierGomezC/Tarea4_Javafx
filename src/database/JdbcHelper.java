/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class JdbcHelper {
    
    //Consular
    public ResultSet realizarConsulta(String query){
        ConexionMySQL conexionMySql = new ConexionMySQL();
        Connection conn = conexionMySql.conectar();
        ResultSet rs = null;
        Statement stQuery;
        try {
            stQuery = conn.createStatement();
            rs = stQuery.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Ejecutar: "+e);
        }
        return rs;
    }
    
    //Insertar, Modificar, Eliminar
    public boolean ejecutarQuery(String query){
        ConexionMySQL conexionMySql = new ConexionMySQL();
        Connection conn = conexionMySql.conectar();
        boolean exito = false;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            int affectedRows = ps.executeUpdate();
            if(affectedRows != 0)
                exito = true;
            else
                exito = false;
            System.err.println("Affected rows: "+affectedRows);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Ejecutar: "+e);
            exito = false;
        }
        return exito;
    }
}
