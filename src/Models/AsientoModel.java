/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Conexion.conector;
import Entidades.AsientoTB;
import Entidades.Render;
//import Formularios.AsientosConatables;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andy
 */
public class AsientoModel {
   
    private conector ObjConexion;
    
    public AsientoModel() {
        ObjConexion = new conector();
    }
    
    public DefaultTableModel obtenerDatos() throws SQLException{
        AsientoTB datos = new AsientoTB();

        DefaultTableModel model = new DefaultTableModel();

           
            
        
        String[] columnNames = {"N op°","fecha","descripcion","cargo", "abono" , "saldo","estado","eliminar"};
        model.setColumnIdentifiers(columnNames);
        // Código a ejecutar si no hay errores
        String sql = "SELECT " +
                "op, "+
                 "fecha , " +
                 "descrip, "+
                "cargo,"+
                 "abono, "+
                 "saldoinicial,estado "+
                 "from librobanco";            
//                 "where ctcodigo_cuenta = ";
            
            Connection conexion = this.ObjConexion.getConexion();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            ResultSet resultados = pstm.executeQuery();
            
            
            while(resultados.next()){  

                datos.setNP(resultados.getString("op"));
                datos.setFechaop(resultados.getString("fecha"));       
                datos.setDesc(resultados.getString("descrip"));
                datos.setCargo(resultados.getString("cargo"));
                datos.setAbono(resultados.getString("abono"));
                datos.setSaldoi(resultados.getString("saldoinicial"));
                datos.setEstado(resultados.getString("estado"));

               model.addRow(new Object[]{datos.getNP(), 
                   datos.getFechaop(),
                   datos.getDesc(),
                   datos.getCargo(),
                   datos.getAbono(),
                   datos.getSaldoi(),
                   datos.getEstado()
                   
                         });
            }       
            return model;
             
        
    
    }
    
    
    
    
    
}
