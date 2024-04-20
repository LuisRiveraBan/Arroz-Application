package com.rakteamentertainment.arrozaplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rakteamentertainment.arrozaplication.DAO.ProductoDAO;
import com.rakteamentertainment.arrozaplication.DAO.ResumenDAO;
import com.rakteamentertainment.arrozaplication.Entidades.Producto;
import com.rakteamentertainment.arrozaplication.Entidades.Resumen;
import com.rakteamentertainment.arrozaplication.MainActivity;
import com.rakteamentertainment.arrozaplication.R;
import com.rakteamentertainment.arrozaplication.databinding.ActivityProductoactivity2Binding;
import com.rakteamentertainment.arrozaplication.databinding.ActivityRegistroproducto2Binding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class registroproductoActivity2 extends AppCompatActivity {

    private ActivityRegistroproducto2Binding binding;
    private ActivityProductoactivity2Binding binding2;

    List<Double> listaPesos = new ArrayList<>();

    void Suma(){
        double suma = sumarLista(listaPesos);
        binding.edtsuma.setText(String.valueOf(suma));;

    }

    public static int sumarLista(List<Double> lista) {
        int suma = 0;

        // Recorrer la lista y sumar los elementos
        for (Double numero : lista) {
            suma += numero;
        }

        return suma;
    }

    private List<Double> obtenerPesos(){

        double peso1 =  Double.parseDouble(binding.edt1.getText()+"");
        double peso2 = Double.parseDouble(binding.edt2.getText()+"");
        double peso3 =  Double.parseDouble(binding.edt3.getText()+"");
        double peso4 =  Double.parseDouble(binding.edt4.getText()+"");
        double peso5 =   Double.parseDouble(binding.edt5.getText()+"");
        double peso6 =   Double.parseDouble(binding.edt6.getText()+"");
        double peso7 =    Double.parseDouble(binding.edt7.getText()+"");
        double peso8 =  Double.parseDouble(binding.edt8.getText()+"");
        double peso9 =   Double.parseDouble(binding.edt9.getText()+"");
        double peso10 =   Double.parseDouble(binding.edt10.getText()+"");

        listaPesos.add(peso1);
        listaPesos.add(peso2);
        listaPesos.add(peso3);
        listaPesos.add(peso4);
        listaPesos.add(peso5);
        listaPesos.add(peso6);
        listaPesos.add(peso7);
        listaPesos.add(peso8);
        listaPesos.add(peso9);
        listaPesos.add(peso10);
        Suma();
        Log.d("DatosIngresados","Lista de pesos: " + listaPesos.toString());
        Toast.makeText(this, "Datos almacenados", Toast.LENGTH_LONG).show();
        return listaPesos;

    }

    private void grabarPesos(){
        SharedPreferences sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE); // Nombre de preferencias
        int IdProducto = sharedPreferences.getInt("clave_id", 0);
        int IdUsuario = sharedPreferences.getInt("clave_usuario", 0);
        Integer valorProducto = Integer.valueOf(IdProducto);
        Integer valorPUsuario = Integer.valueOf(IdUsuario);
        Integer id = valorProducto;



        // Crear el objeto Producto con el ID, usuario y el array de pesos convertido
        Producto producto = new Producto(id, valorPUsuario);
        Log.d("DatosIngresados", "ID: " + id + ", Usuario: " + valorPUsuario + ", Lista de pesos: " + listaPesos.toString());
        ProductoDAO dao = new ProductoDAO(this);
        String Mensaje = dao.GrabarProducto(producto, listaPesos);
        dao = null;
        Toast.makeText(this,Mensaje,Toast.LENGTH_SHORT).show();

    }

    private void Limpiar(){
        binding.edt1.setText("0");
        binding.edt2.setText("0");
        binding.edt3.setText("0");
        binding.edt4.setText("0");
        binding.edt5.setText("0");
        binding.edt6.setText("0");
        binding.edt7.setText("0");
        binding.edt8.setText("0");
        binding.edt9.setText("0");
        binding.edt10.setText("0");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroproducto2Binding.inflate(getLayoutInflater()) ;
        binding2 = ActivityProductoactivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Suma();
        binding.btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerPesos();

            }
        });

        binding.btnLimpiar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Limpiar();
            }
        });

        binding.btnGuardar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarPesos();
                grabarResumen();
                Intent x = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(x);
            }
        });
    }


    // Obtener la fecha actual
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    String fechaActual = dateFormat.format(new Date());

    // Obtener la hora actual
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    String horaActual = timeFormat.format(new Date());

    private void grabarResumen(){
        SharedPreferences sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE); // Nombre de preferencias
        int IdProducto = sharedPreferences.getInt("clave_id", 0);
        int IdUsuario = sharedPreferences.getInt("clave_usuario", 0);
        int Id =sharedPreferences.getInt("clave_id",0);
        int sacos = sharedPreferences.getInt("Sacos", 0);
        String precioString = sharedPreferences.getString("Precio", "0.0"); // El segundo parámetro es el valor por defecto
        double precioDouble = Double.parseDouble(precioString);
        String taraString = sharedPreferences.getString("Tara", "0.0"); // El segundo parámetro es el valor por defecto
        double taraDouble = Double.parseDouble(taraString);
        Integer valorProducto = Integer.valueOf(IdProducto);
        Integer valorPUsuario = Integer.valueOf(IdUsuario);
        Integer id = valorProducto;

        ProductoDAO dao = new ProductoDAO(this);

        double calculo = dao.CalculoSacos(listaPesos,sacos,taraDouble,precioDouble);

        double suma = sumarLista(listaPesos);

        // Crear el objeto Producto con el ID, usuario y el array de pesos convertido
        Resumen resumen = new Resumen(Id,fechaActual,horaActual,valorPUsuario,valorProducto,calculo,sacos,taraDouble,suma,precioDouble);
        Log.d("DatosIngresados", "ID: " + id + ", Usuario: " + valorPUsuario + "Dinero: " + calculo + "Precio: " + precioDouble + "suma: " + suma);
        ResumenDAO daoresumen = new ResumenDAO(this);
        String Mensaje = daoresumen.GrabarResumen(resumen);
        dao = null;
        daoresumen = null;
        Toast.makeText(this,Mensaje,Toast.LENGTH_SHORT).show();

    }


}