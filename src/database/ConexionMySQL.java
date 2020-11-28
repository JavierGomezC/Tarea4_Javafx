/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author user
 */
public class ConexionMySQL {
    public String database = "mbox_iptv";
    public String url = "jdbc:mysql://104.214.100.69:3306/"+database;
    public String user = "foo2";
    public String pass = "A-control1234";
    
    public Connection conectar () {
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Conectar: "+e);
        }
        return link;
        
        /*try { 
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://150.136.162.215:5432/MvcMovie",
                    "postgres", "user_net_web_app_2020_UFPS");
 
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
            return null;
        }*/
    }
}
