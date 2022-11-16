/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Andy
 */

public class conector {
    String url = "src\\Ficheros\\database.db";
    Connection connect = null;
    public String msj;


    public Connection conectar(){
        try {
         connect = DriverManager.getConnection("jdbc:sqlite:"+url);
            if (connect!=null) {
              msj="Conectado";
        }
        }catch (SQLException ex) {
                 msj="No se ha podido conectar a la base de datos\n";
        }
        return connect;
    }

    public Connection getConexion() {
        return conectar();
    }

    public void close(){
        try {
            connect.close();
        } catch (SQLException ex) {
                        System.out.println("No papa  revise");

        }
    }
    
}
