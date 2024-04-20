package com.rakteamentertainment.arrozaplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rakteamentertainment.arrozaplication.Entidades.Resumen;
import com.rakteamentertainment.arrozaplication.Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ResumenDAO {

    SQLiteDatabase db;

    DatabaseHelper helper;

    public ResumenDAO(Context context) {helper = new DatabaseHelper(context);}

    public String GrabarResumen(Resumen obj){
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",obj.getId());
        cv.put("fecha",obj.getFecha());
        cv.put("hora",obj.getHora());
        cv.put("id_usuario",obj.getIdUsuario());
        cv.put("id_producto",obj.getIdProducto());
        cv.put("total_dinero",obj.getTotalDinero());
        cv.put("total_sacos",obj.getTotalSacos());
        cv.put("tara",obj.getTara());
        cv.put("suma_total_sacos",obj.getSumaSacos());
        cv.put("precio",obj.getPrecio());
        db.insert("resumen",null,cv);
        db.close();
        return "Resumen grabado existosamente";

    }

    public String Actualizar(Resumen obj){
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",obj.getId());
        cv.put("fecha",obj.getFecha());
        cv.put("hora",obj.getHora());
        cv.put("id_usuario",obj.getIdUsuario());
        cv.put("id_producto",obj.getIdProducto());
        cv.put("total_dinero",obj.getTotalDinero());
        cv.put("total_sacos",obj.getTotalSacos());
        cv.put("tara",obj.getTara());
        cv.put("suma_total_sacos",obj.getSumaSacos());
        cv.put("precio",obj.getPrecio());
        db.update("resumen",cv,"Id = " + obj.getId(), null);
        db.close();
        return "Se ha Actualizado correctamente";

    }
    public String Eliminar(int codigo) {
        db = helper.getWritableDatabase();

        // Utilizando parámetros en la consulta para evitar inyección de SQL
        db.delete("resumen", "id=?", new String[]{String.valueOf(codigo)});

        db.close();

        return "El Usuario " + codigo + " Se Eliminó Correctamente";
    }



    public List<Resumen> listar(){
        List<Resumen> listarResumen = new ArrayList<>();
        String tablaSql = "SELECT * FROM resumen";
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(tablaSql,null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String fecha = cursor.getString(1);
                String hora = cursor.getString(2);
                Integer usuario = cursor.getInt(3);
                Integer producto = cursor.getInt(4);
                Double total_dinero = cursor.getDouble(5);
                Double total_sacos = cursor.getDouble(6);
                Double tara = cursor.getDouble(7);
                Double suma = cursor.getDouble(8);
                Double precio = cursor.getDouble(9);

                Log.d("DEBUG_TAG", "producto Element: " + producto);

                Resumen resumen = new Resumen(id,fecha,hora,usuario,producto,total_dinero,total_sacos,tara,suma,precio);
                listarResumen.add(resumen);
            }
        }
        cursor.close();
        db.close();
        return listarResumen;
    }


    public List<Resumen> listarBuscar(int idresumen){
        List<Resumen> listarResumen = new ArrayList<>();
        String cadSQL = "SELECT * FROM resumen WHERE id ='" + idresumen + "'";
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(cadSQL,null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String fecha = cursor.getString(1);
                String hora = cursor.getString(2);
                Integer usuario = cursor.getInt(3);
                Integer producto = cursor.getInt(4);
                Double total_dinero = cursor.getDouble(5);
                Double total_sacos = cursor.getDouble(6);
                Double tara = cursor.getDouble(7);
                Double suma = cursor.getDouble(8);
                Double precio = cursor.getDouble(9);


                Resumen resumen = new Resumen(id,fecha,hora,usuario,producto,total_dinero,total_sacos,tara,suma,precio);
                listarResumen.add(resumen);
            }
        }
        cursor.close();
        db.close();
        return listarResumen;
    }
}

