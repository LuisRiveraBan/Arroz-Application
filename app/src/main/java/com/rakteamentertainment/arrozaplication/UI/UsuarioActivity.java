package com.rakteamentertainment.arrozaplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rakteamentertainment.arrozaplication.DAO.UsuarioDAO;
import com.rakteamentertainment.arrozaplication.Entidades.Usuario;
import com.rakteamentertainment.arrozaplication.MainActivity;
import com.rakteamentertainment.arrozaplication.R;
import com.rakteamentertainment.arrozaplication.databinding.ActivityUsuarioBinding;

public class UsuarioActivity extends AppCompatActivity {


    private ActivityUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarUsuario();
                Intent x = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(x);
            }
        });

    }

    private  void grabarUsuario(){
        Usuario obj = new Usuario(
                Integer.parseInt(binding.edtIdUsuario.getText().toString()),
                binding.edtNombre.getText().toString(),
                binding.edtApellido.getText().toString(),
                binding.edtCorreo.getText().toString(),
                binding.edtCelular.getText().toString(),
                binding.edtImg.getText().toString()
        );
        UsuarioDAO dao = new UsuarioDAO(this);
        String mensaje = dao.GrabarUsuario(obj);
        dao = null;
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}