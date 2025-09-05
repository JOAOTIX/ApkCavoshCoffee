package pe.edu.senati.ApkCavoshCoffee.model;

import android.database.Cursor;

public class Producto {
    int id,Categoria,Nuevo,Favorito;
    String Detalle, Descripcion;
    double Precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int categoria) {
        Categoria = categoria;
    }

    public int getNuevo() {
        return Nuevo;
    }

    public void setNuevo(int nuevo) {
        Nuevo = nuevo;
    }

    public int getFavorito() {
        return Favorito;
    }

    public void setFavorito(int favorito) {
        Favorito = favorito;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public boolean isNuevo(){
        return Nuevo==1;
    }
    public boolean isFavorito(){
        return Favorito==1;
    }

    public Producto(Cursor cursor) {
        id= cursor.getInt(0);
        Detalle = cursor.getString(1);
        Descripcion = cursor.getString(2);
        Precio = cursor.getDouble(3);
        Categoria= cursor.getInt(4);
        Nuevo= cursor.getInt(5);
        Favorito = cursor.getInt(6);
    }
}
