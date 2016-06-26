
package controlador;

import javax.swing.table.DefaultTableModel;


public class ProductoModel extends DefaultTableModel{
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    public DefaultTableModel MostrarRegistro() {       
        ProductoModel modelo = new ProductoModel();
        modelo.addColumn("Codigo Barras");
        modelo.addColumn("Nombre");
        modelo.addColumn("Presentacion");
        modelo.addColumn("Grupo");
        modelo.addColumn("Laboratorio");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("stock");
        modelo.addColumn("Precio Compra");
        modelo.addColumn("vencimiento");
        return modelo;
    }
}
