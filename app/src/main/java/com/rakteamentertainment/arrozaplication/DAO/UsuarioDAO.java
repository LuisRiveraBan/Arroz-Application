package com.rakteamentertainment.arrozaplication.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.rakteamentertainment.arrozaplication.Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    SQLiteDatabase db;

    DatabaseHelper helper;

    public UsuarioDAO(Context context) {helper = new DatabaseHelper(context);}

    public String GrabarUsuario(Usuario obj){
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", obj.getId());
        cv.put("nombre",obj.getNombre());
        cv.put("apellido",obj.getApellido());
        cv.put("correo_electronico",obj.getCorreoElectronico());
        cv.put("celular",obj.getCelular());
        cv.put("img_foto",obj.getImgFoto());
        db.insert("usuario",null,cv);

        db.close();
        return "Usuario" + " " + obj.getNombre() + " " + "Se ha registrado correctamente";
    }

    public String Actualizar(Usuario obj){

        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", obj.getId());
        cv.put("nombre",obj.getNombre());
        cv.put("apellido",obj.getApellido());
        cv.put("correo_electronico",obj.getCorreoElectronico());
        cv.put("celular",obj.getCelular());
        cv.put("img_foto",obj.getImgFoto());
        db.update("usuario",cv,"Id = " + obj.getId(), null);

        db.close();
        return "Usuario" + " " + obj.getNombre() + " " + "Se ha Actualizado correctamente";

    }

    public  String Eliminar(int codigo){

        db = helper.getWritableDatabase();

        db.delete("usuario" , "id = " + codigo, null);

        db.close();

        return "El Usuario" + codigo + " Se Elimino Correctamente";
    }


    public List<Usuario> listar(){
        List<Usuario> listarUsuario = new ArrayList<>();
        String tablaSql = "SELECT * FROM usuario";
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(tablaSql,null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String apellido = cursor.getString(2);
                String correo = cursor.getString(3);
                String celular = cursor.getString(4);
                String foto = cursor.getString(5);

                Usuario usuario = new Usuario(id,nombre,apellido,correo,celular,foto);
                listarUsuario.add(usuario);
            }
        }
        cursor.close();
        db.close();
        return listarUsuario;
    }

    public List<String> listarNombresUsuariosConID(){
        List<String> nombresUsuarios = new ArrayList<>();
        List<String> nombresConID = new ArrayList<>();
        String tablaSql = "SELECT id, nombre FROM usuario"; // Selecciona el ID y el nombre de usuario
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(tablaSql, null);
        if(cursor != null && cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex("id");
            int nombreIndex = cursor.getColumnIndex("nombre");
            do {
                if (idIndex != -1 && nombreIndex != -1) {
                    int idUsuario = cursor.getInt(idIndex);
                    String nombre = cursor.getString(nombreIndex);
                    nombresUsuarios.add(nombre);
                    String nombreConID = nombre + " (ID: " + idUsuario + ")";
                    nombresConID.add(nombreConID);
                } else {
                    // Manejo de error si la columna "id" o "nombre" no existe
                    // Puedes imprimir un mensaje de error o manejarlo según tus necesidades
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return nombresConID;
    }



}
