
package controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;

public class Informes {
    private DefaultTableModel tmdiario;
    private JTable tabla;
    public Informes(JTable tabla){
        this.tabla=tabla;
        this.tmdiario = (DefaultTableModel)tabla.getModel();
    }
    public int[] mostrarInforme(String fecha){
        int[] totales = new int[2];
        BD bd= new BD();
        ResultSet rs=bd.consultaDiario(fecha);
        try {
            while(rs.next()){
                Object [] fila=new Object[4];
                fila[0] = rs.getString("fecha");
                fila[1] = rs.getString("id_venta");
                fila[2] = rs.getString("total_venta");
                fila[3] = rs.getString("ganancia");
                totales[0] += Integer.parseInt(rs.getString("total_venta"));
                totales[1] += Integer.parseInt(rs.getString("ganancia"));
                tmdiario.addRow(fila);
            }
            bd.cerrar();
            return totales;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(tabla, "No hay registro de ventas");
        }
        return new int[]{0,0};
    }
    public int venta_Diaria(String fecha){
        int total = 0;
        BD bd= new BD();
        ResultSet rs=bd.consultaVentaDiaria(fecha);
        try {
            while(rs.next()){
                Object [] fila=new Object[7];
                fila[0] = rs.getString("fecha");
                fila[1] = rs.getString("id_venta");
                fila[2] = rs.getString("id_producto");
                fila[3] = rs.getString("nombre");
                fila[4] = rs.getString("precio_venta");
                fila[5] = rs.getString("cantidad");
                fila[6] = rs.getString("total");
                total += Integer.parseInt(rs.getString("total"));
                tmdiario.addRow(fila);
            }
            bd.cerrar();
            return total;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(tabla, "No hay registro de ventas");
        }
        return 0;
    }
    public int[] mostrarInformeAle(String fechaini, String fechafin){
        int[] totales = new int[2];
        totales[0] = 0;
        totales[1] = 0;
        BD bd= new BD();
        ResultSet rs=bd.consultaAleatorio(fechaini,fechafin);
        try {
            while(rs.next()){
                Object [] fila=new Object[4];
                fila[0] = rs.getString("fecha");
                fila[1] = rs.getString("id_venta");
                fila[2] = rs.getString("total_venta");
                fila[3] = rs.getString("ganancia");
                totales[0] += Integer.parseInt(rs.getString("total_venta"));
                totales[1] += Integer.parseInt(rs.getString("ganancia"));
                tmdiario.addRow(fila);
            }
            bd.cerrar();
            return totales;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(tabla, "No hay registro de ventas");
        }
        return new int[]{0,0};
    }
}
