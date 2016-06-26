
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


public class Clientes {

    private BD bd = new BD();
    private String nit, nom, tipo, dir, tel, cel, email;

    public Clientes() {
    }

    public Clientes(String nit, String nom, String tipo, String dir, String tel, String cel, String email) {
        this.nit = nit;
        this.nom = nom;
        this.tipo = tipo;
        this.dir = dir;
        this.tel = tel;
        this.cel = cel;
        this.email = email;
    }

    public boolean Insertar() {
        boolean comp = true;
        try {
            ResultSet cant = bd.Consult_Id(nit, "cliente");
            if (cant.next()) {
                JOptionPane.showMessageDialog(null, "El Nit del Cliente ya existe");
                comp= false;
            } else {
                bd.Insert_Cliente(nit, nom, tipo, dir, tel, cel, email);
                comp=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comp;
    }

    public void View(DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);
            ResultSet rs = bd.filtro_cli(nit, nom, tipo, dir, tel, cel, email);
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

    public void Mod(String id, JTextField nit, JTextField nom, JTextField tipo, JTextField dir, JTextField tel, JTextField cel, JTextField email) {

        try {
            ResultSet rs = bd.Consult_Id(id, "cliente");

            while (rs.next()) {
                nit.setText(rs.getObject(1).toString());
                nom.setText(rs.getObject(2).toString());
                tipo.setText(rs.getObject(3).toString());
                dir.setText(rs.getObject(4).toString());
                tel.setText(rs.getObject(5).toString());
                cel.setText(rs.getObject(6).toString());
                email.setText(rs.getObject(7).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update(String id) {
        bd.Update_Cliente(id, nit, nom, tipo, dir, tel, cel, email);
    }

    public void Drop(ArrayList<String> id) {
        for (int i = 0; i < id.size(); i++) {
            bd.Drop(id.get(i),"cliente");
        }
        JOptionPane.showMessageDialog(null, "El Cliente o Los clientes seleccionados, fueron eliminados");
    }
}
