/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author Admin
 */
public class Producto {

    private String codigob;
    private String nombre;
    private String presentacion;
    private int grupo;
    private int laboratorio;
    private int stock;
    private int venta;
    private int compra;

    public Producto(String codigo_barras,String nombre, String presentacion, int id_grupo, int id_lab, int precio_venta, int stock, int preciomaxcompra) {
        this.codigob = codigo_barras;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.grupo = id_grupo;
        this.laboratorio = id_lab;
        this.stock = stock;
        this.venta = precio_venta;
        this.compra = preciomaxcompra;
    }

    public void setCodigo(String codigo) {
        this.codigob = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public void setLaboratorio(int laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public String getCodigob() {
        return codigob;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public int getGrupo() {
        return grupo;
    }

    public int getLaboratorio() {
        return laboratorio;
    }

    public int getStock() {
        return stock;
    }

    public int getVenta() {
        return venta;
    }

    public int getCompra() {
        return compra;
    }

}
