/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;

/**
 *
 * @author Admin
 */
public class ControlProducto {

    BD bd = new BD();

    String nombre, descripcion, grupo, lab, cod, p_compra, p_venta, stock;
    String Nom1, vence;
    
    public ControlProducto(){
    
    }
    public ArrayList combox(String campo1,String campo2, String tabla){
        ArrayList<String> combo = new ArrayList();
        ResultSet rs = bd.consultaGeneral(campo1+", "+campo2, tabla);       
        try {           
            int i=0;
            while (rs.next()) {
                String resp= rs.getString(campo1)+". "+rs.getString(campo2);
                combo.add(resp);
                i++;
            }
        } catch (SQLException ex) {
            combo.add("No hay "+campo1);
        }
        return combo;
    }
    public ControlProducto(String campo3, String campo4, String pres, String lab, String campo7, String campo1, String campo8, String campo11, String vence) {
        this.nombre = campo3;
        this.descripcion = campo4;
        this.grupo = pres;
        this.lab = lab;
        this.cod = campo7;
        this.p_compra = campo1;
        this.p_venta = campo8;
        this.stock = campo11;
        this.vence = vence;
    }

    public String ejecutarAccion(String accion) {
        String resp;
        if (nombre.equals("")) {
            resp = "Casilla nombre no puede estar vacia";
        }else if (descripcion.equals("")) {
            resp = "Casilla descripcion no puede estar vacia";
        }else if (grupo.equals("")) {
            resp = "Casilla grupo no puede estar vacia";
        }else if (lab.equals("")) {
            resp = "Casilla laboratorio no puede estar vacia";
        }else if (cod.equals("")) {
            resp = "Casilla codigo de barras no puede estar vacia";
        }else if (p_compra.equals("")) {
            resp = "Casilla precio compra no puede estar vacia";
        }else if (p_venta.equals("")) {
            resp = "Casilla precio venta no puede esta vacia";
        }else if (stock.equals("")) {
            resp = "Casilla stock no puede estar vacia";
        }else if (vence.equals("")) {
            resp = "Casilla Fecha de vencimiento no puede estar vacia";
        }else{
            if (accion.equals("insert")) {
                bd.InsertarProducto(nombre, descripcion, grupo, lab, cod, p_compra, p_venta, stock, vence);      
            } else if(accion.equals("update")){
                bd.ActualizarProducto(nombre, descripcion, grupo, lab, cod, p_compra, p_venta, stock, vence);
            }
            resp="Registro exitoso";
        }
        return resp;
    }

    public void Guardar(DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);
            ResultSet rs = bd.ConsultarProducto("producto");
            ResultSetMetaData Datos = rs.getMetaData();
            int NumCols = Datos.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[NumCols];
                for (int i = 0; i < NumCols; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar producto..?");
        }
    }

    public void Mostrar(DefaultTableModel modelo, String nom1) {
        try {
            modelo.setRowCount(0);
            ResultSet rs = bd.ConsultarProductoNom(nom1);
            ResultSetMetaData Datos = rs.getMetaData();
            int NumCols = Datos.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[NumCols];
                for (int i = 0; i < NumCols; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar producto..?");
        }
    }
    
    public void Eliminar(String cod) {
        if(cod.equals("")){
            JOptionPane.showMessageDialog(null, "No ha seleccionado el producto a eliminar");
        }else{
            bd.EliminarProductoNom(cod);
        }
    }
}
    
