/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Conexion.conector;
import Entidades.AsientoTB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eterna
 */
public class AsientoModel2 {
    private conector ObjConexion;
    
    public AsientoModel2() {
        ObjConexion = new conector();
    }
    
    public DefaultTableModel obtenerDatos() throws SQLException{
        AsientoTB datos = new AsientoTB();

        DefaultTableModel model = new DefaultTableModel();

           
            
        
        String[] columnNames = {"N op°","fecha","descripcion","abono", "cargo" , "saldo","estado","eliminar"};
        model.setColumnIdentifiers(columnNames);
        // Código a ejecutar si no hay errores
        String sql = "SELECT " +
                "op, "+
                 "fecha , " +
                 "descrip, "+
                "abono,"+
                 "cargo, "+
                 "saldoinicial,estado "+
                 "from estadocuenta";            
//                 "where ctcodigo_cuenta = ";
            
            Connection conexion = this.ObjConexion.getConexion();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            ResultSet resultados = pstm.executeQuery();
            
            
            while(resultados.next()){  

                datos.setNP(resultados.getString("op"));
                datos.setFechaop(resultados.getString("fecha"));       
                datos.setDesc(resultados.getString("descrip"));
                  datos.setAbono(resultados.getString("abono"));
                datos.setCargo(resultados.getString("cargo"));           
                datos.setSaldoi(resultados.getString("saldoinicial"));
                datos.setEstado(resultados.getString("estado"));


               model.addRow(new Object[]{datos.getNP(), 
                   datos.getFechaop(),
                   datos.getDesc(),                   
                   datos.getAbono(),
                   datos.getCargo(),                  
                   datos.getSaldoi(),
                   datos.getEstado()
                         });
            }       
            return model;
    }
}
