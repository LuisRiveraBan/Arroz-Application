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
import com.rakteamentertainment.arrozaplication.R;

import java.util.List;

public class ResumenAdapter2 extends ArrayAdapter<Resumen> {

    Context miContext;
    int milayout;

    List<Resumen> milista;


    public ResumenAdapter2(@NonNull Context context, int resource, @NonNull List<Resumen> objects){
        super(context,resource, objects);
        miContext = context;
        milayout = resource;
        milista = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater mi_inflater = LayoutInflater.from(miContext);
        convertView = mi_inflater.inflate(milayout, null);
        TextView Sacos = convertView.findViewById(R.id.edtTotalSacosResumen);
        TextView Tara = convertView.findViewById(R.id.edtTaraResumen);
        TextView Suma = convertView.findViewById(R.id.edtSumaSacosResumen);

        Resumen obj = milista.get(position);


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


        if (Suma != null) {
            double sumaDouble = obj.getSumaSacos();
            String sumaString = String.valueOf(sumaDouble);
            Suma.setText("SUMA TOTAL DE LOS SACOS: " + sumaString);
        }

        return convertView;
    }
}
