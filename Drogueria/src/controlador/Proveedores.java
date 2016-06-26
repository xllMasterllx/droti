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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.*;

/**
 *
 * @author Yoiimar
 */
public class Proveedores {

    private BD bd = new BD();
    private String nit, nom, dir, tel1, tel2, cel, obs;

    public Proveedores() {
    }

    public Proveedores(String nit, String nom, String dir, String tel1, String tel2, String cel, String obs) {
        this.nit = nit;
        this.nom = nom;
        this.dir = dir;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.cel = cel;
        this.obs = obs;
    }

    public boolean Insertar() {
        boolean camp = true;
        try {
            ResultSet cant = bd.Consult_Id(nit, "proveedores");
            if (cant.next()) {
                JOptionPane.showMessageDialog(null, "El Nit del Cliente ya existe");
                camp = false;
            } else {
                bd.Insert_Proveedor(nit, nom, dir, tel1, tel2, cel, obs);
                camp = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return camp;
    }

    public void View(DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);
            ResultSet rs = bd.filtro_prov(nit, nom, dir, tel1, tel2, cel, obs);
            ResultSetMetaData metaDatos = rs.getMetaData();
            int NumCols = metaDatos.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[NumCols];

                for (int i = 0; i < NumCols; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Mod(String id, JTextField nit, JTextField nom, JTextField dir, JTextField tel1, JTextField tel2, JTextField cel, JTextField obs) {

        try {
            ResultSet rs = bd.Consult_Id(id, "proveedores");

            while (rs.next()) {
                nit.setText(rs.getObject(1).toString());
                nom.setText(rs.getObject(2).toString());
                dir.setText(rs.getObject(3).toString());
                tel1.setText(rs.getObject(4).toString());
                tel2.setText(rs.getObject(5).toString());
                cel.setText(rs.getObject(6).toString());
                obs.setText(rs.getObject(7).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update(String id) {
        bd.Update_Proveedor(id, nit, nom, dir, tel1, tel2, cel, obs);
    }

    public void Drop(ArrayList<String> id) {
        for (int i = 0; i < id.size(); i++) {
            bd.Drop(id.get(i), "proveedores");
        }
        JOptionPane.showMessageDialog(null, "El Proveedor o los Proveedores seleccionados, fueron eliminados");
    }

}
