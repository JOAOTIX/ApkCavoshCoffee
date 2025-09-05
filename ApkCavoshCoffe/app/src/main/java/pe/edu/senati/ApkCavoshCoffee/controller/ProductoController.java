package pe.edu.senati.ApkCavoshCoffee.controller;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import pe.edu.senati.ApkCavoshCoffee.db.DB;
import pe.edu.senati.ApkCavoshCoffee.model.Producto;

public class ProductoController {
    DB db;
    Producto producto;

    public ProductoController(Context context){
        db =new DB(context);
    }
    public List<Producto> getProductos(String sWhere){
        db.sentencia(String.format("select * from Producto where %s = 1",sWhere));
        Cursor cursor =db.getCursor();

        List<Producto> productos=null;
        while (cursor.moveToNext()){
            productos.add(new Producto(cursor));
        }
        cursor.close();
        return productos.isEmpty()?null:productos;
    }

}
