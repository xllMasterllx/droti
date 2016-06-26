/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;


/**
 *
 * @author castro
 */
public class Ventas {
   private int stock;
   private String codigo;
   private int cantidad;
   private String nombre;
   private int valor;
   private int total;



    public Ventas(int stock, String codigo, int cantidad, String nombre, int valor) {
        this.stock = stock;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.valor = valor;
        this.total = cantidad*valor;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStock() {
        return stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    public int getTotal() {
        return total;
    }
    
}
