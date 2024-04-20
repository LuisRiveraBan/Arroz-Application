package com.rakteamentertainment.arrozaplication.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.rakteamentertainment.arrozaplication.DAO.DatabaseHelper;
import com.rakteamentertainment.arrozaplication.Entidades.Resumen;
import com.rakteamentertainment.arrozaplication.Entidades.Usuario;
import com.rakteamentertainment.arrozaplication.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResumenAdapter extends ArrayAdapter<Resumen> {

    Context miContext;
    int milayout;

    List<Resumen> milista;

    SQLiteDatabase db;
    DatabaseHelper helper;

    public ResumenAdapter(@NonNull Context context, int resource, @NonNull List<Resumen> objects){
        super(context,resource, objects);
        miContext = context;
        milayout = resource;
        milista = objects;
        helper = new DatabaseHelper(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater mi_inflater = LayoutInflater.from(miContext);
        convertView = mi_inflater.inflate(milayout, null);
        TextView Nombre = convertView.findViewById(R.id.edtUsuarioNombre);
        TextView Fecha = convertView.findViewById(R.id.edtFechaResumen);
        TextView Hora = convertView.findViewById(R.id.edtHoraResumen);
        TextView Dinero = convertView.findViewById(R.id.edtTotalDineroResumen);
        TextView Sacos = convertView.findViewById(R.id.edtTotalSacosResumen);
        TextView Tara = convertView.findViewById(R.id.edtTaraResumen);
        TextView Precio = convertView.findViewById(R.id.edtPrecioResumen);
        TextView Suma = convertView.findViewById(R.id.edtSumaSacosResumen);

        Resumen obj = milista.get(position);

        // Ejemplo de verificación para asegurarse de que las vistas no son nulas
        if (Nombre != null) {
            int usuario = obj.getIdUsuario();

            String tablaSql = "SELECT id, nombre FROM usuario WHERE id ='" + usuario  + "'";
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery(tablaSql, null);
            if(cursor != null && cursor.moveToFirst()){
                int nombreIndex = cursor.getColumnIndex("nombre");
                do {
                        String nombre = cursor.getString(nombreIndex);
                        Nombre.setText("VENDEDOR: " + nombre);

                } while (cursor.moveToNext());
                cursor.close();
            }
            db.close();
        }

        if (Fecha != null) {
            Fecha.setText("FECHA: " + obj.getFecha());
        }

        if (Hora != null) {
            Hora.setText("HORA: " + obj.getHora());
        }

        if (Dinero != null) {
            double totalDinero = obj.getTotalDinero();
            String dineroString = String.valueOf(totalDinero);
            Dinero.setText("TOTAL DE DINERO A RECIBIR: " + dineroString);
        }

        if (Sacos != null) {
            double totalSacos = obj.getTotalSacos();
            String totalSacosString = String.valueOf(totalSacos);
            Sacos.setText("TOTAL DE SACOS: " + totalSacosString);
        }

        if (Tara != null) {
            double taraDouble = obj.getTara();
            String taraString = String.valueOf(taraDouble);
            Tara.setText("TARA: " + taraString);
        }

        if (Precio != null) {
            double precioDouble = obj.getPrecio();
            String precioString = String.valueOf(precioDouble);
            Precio.setText("PRECIO: " + precioString);
        }

        if (Suma != null) {
            double sumaDouble = obj.getSumaSacos();
            String sumaString = String.valueOf(sumaDouble);
            Suma.setText("SUMA TOTAL DE LOS SACOS: " + sumaString);
        }

        return convertView;
    }
}
