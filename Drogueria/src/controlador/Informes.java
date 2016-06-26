/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
/**
 *
 * @author Master
 */
public class Informes {
    private DefaultTableModel tmdiario;
    private JTable tabla;
    public Informes(JTable tabla){
        this.tabla=tabla;
        this.tmdiario = (DefaultTableModel)tabla.getModel();
    }
    public int mostrarInforme(String fecha){
        int total = 0;
        BD bd= new BD();
        ResultSet rs=bd.consultaDiario(fecha);
        try {
            while(rs.next()){
                Object [] fila=new Object[3];
                fila[0] = rs.getString("id_venta");
                fila[1] = rs.getString("fecha");
                fila[2] = rs.getString("total_venta");
                total += Integer.parseInt(rs.getString("total_venta"));
                tmdiario.addRow(fila);
            }
            bd.cerrar();
            return total;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(tabla, "No hay registro de ventas");
        }
        return 0;
    }
}
