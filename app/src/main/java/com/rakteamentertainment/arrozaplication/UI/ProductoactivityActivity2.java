package com.rakteamentertainment.arrozaplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rakteamentertainment.arrozaplication.DAO.UsuarioDAO;
import com.rakteamentertainment.arrozaplication.R;
import com.rakteamentertainment.arrozaplication.databinding.ActivityProductoactivity2Binding;

import java.util.List;


public class ProductoactivityActivity2 extends AppCompatActivity {

    private ActivityProductoactivity2Binding binding;

    EditText Id, Sacos, Tara,Precio;
    Spinner usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductoactivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Id = findViewById(R.id.edtId);
        usuario = findViewById(R.id.spinnerUsuario);
        Sacos = findViewById(R.id.edtTotalSacos);
        Tara = findViewById(R.id.edtTara);
        Precio = findViewById(R.id.edtPrecio);


        mostrar();

        binding.btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                almecenarid();
                Intent x = new Intent(getApplicationContext(), registroproductoActivity2.class);
                startActivity(x);
            }
        });



    }


    public void almecenarid(){
        SharedPreferences sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE); // Nombre de preferencias
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Integer id = Integer.parseInt(Id.getText().toString());
        int sacos = Integer.parseInt(Sacos.getText().toString());
        double tara = Integer.parseInt(Tara.getText().toString());
        double precio = Double.parseDouble(Precio.getText().toString());
        UsuarioDAO dao = new UsuarioDAO(this);
        List<String> nombresConID = dao.listarNombresUsuariosConID();

        // Obtener la posición seleccionada en el Spinner
        int posicionSeleccionada = binding.spinnerUsuario.getSelectedItemPosition();

        // Verificar que la posición seleccionada sea válida
        if (posicionSeleccionada != AdapterView.INVALID_POSITION && posicionSeleccionada < nombresConID.size()) {
            // Obtener el elemento (nombre con ID) seleccionado en el Spinner
            String nombreConIDSeleccionado = nombresConID.get(posicionSeleccionada);

            // Extraer el ID del texto seleccionado
            int inicioID = nombreConIDSeleccionado.indexOf("(ID: ") + "(ID: ".length();
            int finID = nombreConIDSeleccionado.indexOf(")", inicioID);

            if (inicioID != -1 && finID != -1) {
                // Obtener la subcadena que representa el ID
                String idUsuarioString = nombreConIDSeleccionado.substring(inicioID, finID);

                // Convertir la cadena a un entero
                int idUsuario = Integer.parseInt(idUsuarioString);

                // Almacenar el ID del usuario en SharedPreferences
                String precioString = String.valueOf(precio);
                String taraString = String.valueOf(tara);
                editor.putString("Precio", precioString); // Almacenar el valor como String
                editor.putInt("Sacos", sacos);
                editor.putString("Tara", taraString);
                editor.putInt("clave_id", id);
                editor.putInt("clave_usuario", idUsuario);
                editor.apply();

                Toast.makeText(this, "ID almacenado: " + idUsuario, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "No se seleccionó un usuario válido", Toast.LENGTH_LONG).show();
        }
    }





    void mostrar(){
        UsuarioDAO dao = new UsuarioDAO(this);
        List<String> nombresConID = dao.listarNombresUsuariosConID();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresConID);

        binding.spinnerUsuario.setAdapter(adapter);
    }

}