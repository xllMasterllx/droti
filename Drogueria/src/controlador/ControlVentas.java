    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
/**
 *
 * @author castro
 */
public class ControlVentas {
    private String codigo;
    private int cantidad;
    private int total = 0;
    private final int cliente= 1;
    DefaultTableModel t;
    public static ArrayList <Ventas> v = new ArrayList();
    

    public ControlVentas() {
    }
    
    public ControlVentas(String codigo,int cantidad, JTable tabla) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        guardar();
        t = (DefaultTableModel) tabla.getModel();
    }
    public void guardar(){
        BD bd = new BD();
        ResultSet rs=bd.consultaventa(codigo);
        try {
            while (rs.next()){
                Ventas producto = new Ventas((rs.getInt("stock")-cantidad), codigo, cantidad, rs.getString("nombre"), rs.getInt("precio_venta"));
                v.add(producto);
            }
            bd.cerrar();
        } catch (SQLException ex) {
            System.out.println("no hay resulset en controlventas");
        }
    }
   
    public int mostratRegistro(){
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        Ventas producto = (Ventas) v.get(v.size()-1);
        Object [] fila=new Object[6];
        fila[0] = producto.getStock();
        fila[1] = producto.getCodigo();
        fila[2] = producto.getCantidad();
        fila[3] = producto.getNombre();
        fila[4] = formateador.format(producto.getValor());
        fila[5] = formateador.format(producto.getTotal());
        total += producto.getTotal();
        t.addRow(fila);
        System.out.println("conteo "+v.size());
        return producto.getTotal();
    }
    public void buscarProducto(String cod,JLabel nombre,JLabel precio,JLabel stock){
        BD bd = new BD();
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        ResultSet rs=bd.consultaventa(cod);
        try {
            while (rs.next()){
                nombre.setText(rs.getString("nombre"));
                precio.setText(formateador.format(Integer.parseInt(rs.getString("precio_venta"))));
                stock.setText(rs.getString("stock"));
            }
            bd.cerrar();
        } catch (SQLException ex) {
            System.out.println("no hay resulset en controlventas");
        }
        
    }
    public void guardarTodo(JLabel fecha,int t){
        BD bd = new BD();
        bd.insertVenta(fecha.getText(), cliente, t);
        for (int i = 0; i < v.size(); i++) {
            bd.insertarDetalleV(v.get(i).getCodigo(), v.get(i).getCantidad(), v.get(i).getValor(), v.get(i).getTotal());
            bd.modificarStock(v.get(i).getCodigo(),v.get(i).getStock());
        }
        v.removeAll(v);
        bd.cerrar();
    }
    public void borrarTabla(){
        t.setRowCount(0);
        total = 0;
    }
}
