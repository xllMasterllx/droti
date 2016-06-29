
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BD {
    int id_venta;
    public BD(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/db_drogueria","root","");
            s = conexion.createStatement();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString(), null, JOptionPane.WARNING_MESSAGE);
                }
                
    }
    public void insertVenta(String fecha,int cliente,int total){
                
            try{    
                ResultSet id = s.executeQuery("select max(id_venta) from ventas");
                while(id.next()){
                id_venta = (id.getInt(1)+1);
                }
                s.executeUpdate("Insert into ventas values("+id_venta+",'"+fecha+"',"+cliente+","+total+")");
                //conexion.close();
             } catch(SQLException e){
                 JOptionPane.showMessageDialog(null, "Error en la transaccion con la base de datos ERROR: "+e.toString(), null, JOptionPane.WARNING_MESSAGE);
             }
    }
    public ResultSet consultaventa(String cod){
        try {
            PreparedStatement sentencia = conexion.prepareStatement("select nombre,precio_venta,stock from producto where codigo_barras=?");
            sentencia.setString(1, cod);
            ResultSet reg = sentencia.executeQuery();
            return reg;
        } catch (SQLException ex) {
            System.out.println("Error consulta : "+ex.getLocalizedMessage());
        }
        return null;
    }
    public void insertarDetalleV(String id_p,int cantidad,int precio,int total){
        try {
            s.executeUpdate("Insert into detalleventa (id_venta,id_producto,cantidad,precio_venta,subtotal_venta) values"
                    + "("+id_venta+",'"+id_p+"',"+cantidad+","+precio+","+total+")");
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    public ResultSet consultaDiario(String fecha){
        try {
            PreparedStatement sentencia = conexion.prepareStatement("select id_venta,fecha,total_venta from ventas where fecha=?");
            sentencia.setString(1, fecha);
            ResultSet reg = sentencia.executeQuery();
            return reg;
        } catch (SQLException ex) {
            System.out.println("Error consulta : "+fecha+" "+ex.getLocalizedMessage());
        }
        return null;
    }
    public void cerrar(){
        try {
            conexion.close();
            System.out.println("cerre la conexion");
        } catch (SQLException ex) {
            System.out.println("no cerro la conexion");
        }
    }
    public void modificarStock(String cod,int stock){
        try {
            s.executeUpdate("UPDATE producto SET stock="+stock+" where codigo_barras='"+cod+"'");
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    public void InsertarProducto(String campo1, String campo2, String campo3, String campo4, String campo5, String campo6, String campo7, String campo8, String campo9) {
        try {
            String prod = "INSERT INTO producto(nombre,presentacion,id_grupo,id_lab,codigo_barras,preciomaxcompra,precio_venta,stock,vencimiento) VALUES('" + campo1 + "','" + campo2 + "','" + campo3 + "'," + campo4 + "," + campo5 + "," + campo6 + "," + campo7 + "," + campo8 + ",'"+campo9+"'5 )";
            s.executeUpdate(prod);
        } catch (SQLException ex) {
            System.out.println("Aun hay un ERROR?..." + ex);
        }
    }

    public ResultSet ConsultarProducto(String sql) {
        ResultSet rs = null;
        String query = ("SELECT * FROM " + sql);
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error consulta : " + ex.getLocalizedMessage());
        }
        return rs;
    }

    public ResultSet ConsultarProductoNom(String nombre) {
        try {
            PreparedStatement sentencia = conexion.prepareStatement("select * from producto where nombre like '%"+nombre+"%'");
            ResultSet rs = sentencia.executeQuery();

            return rs;
        } catch (SQLException ex) {
            System.out.println("entro a la excepcion");
            return null;
        }
    }
    
    public void ActualizarProducto(String cod,String nom,String pre,String gru,String lab,String ven,String sto,String com, String f_vence) {        
        try {
            System.out.println("UPDATE producto SET nombre='"+nom+"',presentacion='"+pre+"',id_grupo="+gru+",id_lab="+lab+",precio_venta="+ven+",stock="+sto+",preciomaxcompra="+com+", vencimiento="+f_vence+" WHERE codigo_barras='"+cod+"'");
            String prod = "UPDATE producto SET nombre='"+nom+"',presentacion='"+pre+"',id_grupo="+gru+",id_lab="+lab+",precio_venta="+ven+",stock="+sto+",preciomaxcompra="+com+", vencimiento='"+f_vence+"' WHERE codigo_barras='"+cod+"'";
            s.executeUpdate(prod);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hay un ERROR al Actualizar..." + ex);
        }
    }
    
    public void EliminarProductoNom(String cod) {        
        try {
            String prod = ("DELETE FROM producto WHERE codigo_barras='"+cod+"'");
            s.executeUpdate(prod);
            JOptionPane.showMessageDialog(null, "Registro eliminado con Exito!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hay un ERROR al elmininar..." + ex);
        }
    }
    public ResultSet consultaGeneral(String columnas, String tabla) {
        try {
            PreparedStatement sentencia = conexion.prepareStatement("select "+columnas+" from "+tabla);
            ResultSet reg = sentencia.executeQuery();
            return reg;
        } catch (SQLException ex) {
            System.out.println("Error consulta : " +columnas+" "+tabla+ ex.getLocalizedMessage());
        }
        return null;
    }
    public void Insert_Cliente(String nit, String nom, String tipo, String dir, String tel, String cel, String email) {

        try {
            String sql = "INSERT INTO cliente (nit_cc,nombre,tipo,direccion,telefono,celular,email) VALUES ("
                    + nit + ",'" + nom + "','" + tipo + "','" + dir + "','"
                    + tel + "','" + cel + "','" + email + "')";
            Statement pst = conexion.createStatement();
            pst.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro introducido correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public ResultSet Consult_Id(String nit, String tabla) {
        ResultSet rs = null;
        String view = "Select * from " + tabla + " where nit_cc = " + nit;

        if (tabla.equals("proveedores")) {

            view = "Select * from " + tabla + " where nit = " + nit;
        }

        try {
            PreparedStatement st = conexion.prepareStatement(view);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error consulta : " + ex.getLocalizedMessage());

        }

        return rs;
    }

    public ResultSet filtro_cli(String id, String nom, String tipo, String dir, String tel, String cel, String email) {
        ResultSet rs = null;
        String sql = "SELECT * FROM cliente WHERE nit_cc LIKE'"
                + id + "%' AND nombre LIKE '" + nom + "%' AND tipo LIKE'" + tipo + "%'AND direccion LIKE'" + dir + "%' AND telefono LIKE'"
                + tel + "%' AND celular LIKE'" + cel + "%' AND email LIKE'" + email + "%'";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error consulta : " + ex.getLocalizedMessage());

        }
        return rs;

    }

    public void Update_Cliente(String id, String nit, String nom, String tipo, String dir, String tel, String cel, String email) {

        try {
            String sql = "UPDATE cliente SET nit_cc = "
                    + nit + ",nombre = '" + nom + "',tipo = '" + tipo + "',direccion = '" + dir + "',telefono = '"
                    + tel + "',celular = '" + cel + "',email = '" + email
                    + "' WHERE nit_cc = " + id;
            Statement pst = conexion.createStatement();
            pst.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro Actualizado correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error" + ex);
        }

    }

    public void Drop(String id, String table) {
        try {
            String sql = "DELETE FROM " + table + " WHERE nit_cc= " + id;
            if (table.equals("proveedores")) {
                sql = "DELETE FROM " + table + " WHERE nit= " + id;
            }
            Statement pst = conexion.createStatement();
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void Insert_Proveedor(String nit, String nom, String dir, String tel1, String tel2, String cel, String obs) {

        try {
            String sql = "INSERT INTO proveedores (nit,nombre,direccion,telefono1,telefono2,celular,observacion) VALUES ("
                    + nit + ",'" + nom + "','" + dir + "','" + tel1 + "','"
                    + tel2 + "','" + cel + "','" + obs + "')";
            Statement pst = conexion.createStatement();
            pst.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro introducido correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public ResultSet filtro_prov(String id, String nom, String dir, String tel1, String tel2, String cel, String obs) {
        ResultSet rs = null;
        String sql = "SELECT * FROM proveedores WHERE nit LIKE'"
                + id + "%' AND nombre LIKE '" + nom + "%' AND direccion LIKE'" + dir + "%'AND telefono1 LIKE'" + tel1 + "%' AND telefono2 LIKE'"
                + tel2 + "%' AND celular LIKE'" + cel + "%' AND observacion LIKE'" + obs + "%'";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error consulta : " + ex.getLocalizedMessage());

        }
        return rs;

    }

    public void Update_Proveedor(String id, String nit, String nom, String dir, String tel1, String tel2, String cel, String obs) {

        try {
            String sql = "UPDATE proveedores SET nit = "
                    + nit + ",nombre = '" + nom + "',direccion = '" + dir + "',telefono1 = '" + tel1 + "',telefono2 = '"
                    + tel2 + "',celular = '" + cel + "',observacion = '" + obs
                    + "' WHERE nit = " + id;
            Statement pst = conexion.createStatement();
            pst.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro Actualizado correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error" + ex);
        }

    }
    private Statement s;
    private Connection conexion;
}
