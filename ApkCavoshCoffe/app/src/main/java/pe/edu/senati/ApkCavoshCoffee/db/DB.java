package pe.edu.senati.ApkCavoshCoffee.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    String SQL;
    public DB(@Nullable Context context) {
        super(context,"cavoshcafe.db",null,1);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Usuario(id integer, Nombres text, Correo text, Passwordd text, Login integer)");
        db.execSQL("create table Producto(id integer, Detalle text, Descripción text, Precio real, Categoria integer, Nuevo integer,Favorito integer)");
        db.execSQL("create table Local(id integer, RazonSocial text,Direccion text,idDistrito integer,Distrito text,Horario text,Latitud text,Longitud text)");
        db.execSQL("create table Distrito(id integer,Detalle text)");

        db.execSQL("create table Pedido(id integer,idUsuario integer, idLocal integer,FechaVenta text,Total real,Items integer)");
        db.execSQL("create table PedidoDetalle(idPedido integer, idProducto integer,cantidad integer, size integer,milk integer,cream integer,caffeine integer,total real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void sentencia(String sql) {
        this.SQL=sql;
    }

    public Cursor getCursor() {
        return db.rawQuery(SQL,null);
    }

    public int  Update(String sTabla, ContentValues values, String sCondicion) {
        return db.update(sTabla,values,sCondicion,null);
    }

    public int Insert(String sTabla, ContentValues values) {
        return (int) db.insert(sTabla,null,values);
    }
}

