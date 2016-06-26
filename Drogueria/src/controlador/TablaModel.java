/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yoiimar
 */
public class TablaModel extends DefaultTableModel {
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public DefaultTableModel VerClientes() {
        TablaModel modelo = new TablaModel();
        modelo.addColumn("Nit o CC");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("Celular");
        modelo.addColumn("Email");
        return modelo;
    }
    
     public DefaultTableModel VerProveedores() {
        TablaModel modelo = new TablaModel();
        modelo.addColumn("Nit");
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono 1");
        modelo.addColumn("Telefono 2");
        modelo.addColumn("Celular");
        modelo.addColumn("Observaciones");
        return modelo;
    }
}

