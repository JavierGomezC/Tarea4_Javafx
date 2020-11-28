/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author user
 */
public class Canal {
    private int id;
    private int numero;
    private String nombre;
    private String categoria;
    private String calidad;

    public Canal(int id, int numero, String nombre, String categoria, String calidad) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.categoria = categoria;
        this.calidad = calidad;
    }

    public Canal(int numero, String nombre, String categoria, String calidad) {
        this.numero = numero;
        this.nombre = nombre;
        this.categoria = categoria;
        this.calidad = calidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    @Override
    public String toString() {
        return "Canal{" + "id=" + id + ", numero=" + numero + ", nombre=" + nombre + ", categoria=" + categoria + ", calidad=" + calidad + '}';
    }
    
}
