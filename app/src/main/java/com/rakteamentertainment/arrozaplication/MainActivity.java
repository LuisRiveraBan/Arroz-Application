package com.rakteamentertainment.arrozaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rakteamentertainment.arrozaplication.UI.ProductoactivityActivity2;
import com.rakteamentertainment.arrozaplication.UI.UsuarioActivity;
import com.rakteamentertainment.arrozaplication.UI.activity_listarresumen;
import com.rakteamentertainment.arrozaplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), UsuarioActivity.class);
                startActivity(x);
            }
        });

        binding.btnRegistrarArroz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), ProductoactivityActivity2.class);
                startActivity(x);
            }
        });


        binding.btnResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), activity_listarresumen.class);
                startActivity(x);
            }
        });
    }
}