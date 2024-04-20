package com.rakteamentertainment.arrozaplication.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.rakteamentertainment.arrozaplication.Adaptadores.ResumenAdapter;
import com.rakteamentertainment.arrozaplication.DAO.ProductoDAO;
import com.rakteamentertainment.arrozaplication.DAO.ResumenDAO;
import com.rakteamentertainment.arrozaplication.Entidades.Resumen;
import com.rakteamentertainment.arrozaplication.R;
import com.rakteamentertainment.arrozaplication.databinding.ActivityListarresumenBinding;
import com.rakteamentertainment.arrozaplication.databinding.ActivityProductoactivity2Binding;

public class activity_listarresumen extends AppCompatActivity {

    private ActivityListarresumenBinding binding;

    private ResumenDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarresumenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ListResumen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén el objeto Resumen correspondiente a la posición clicada
                Resumen resumenClicado = (Resumen) parent.getItemAtPosition(position);

                // Ahora puedes acceder al ID del objeto Resumen
                int idResumen = resumenClicado.getId();

                // Haz lo que necesites con el ID (por ejemplo, mostrarlo en un Toast)
                Toast.makeText(getApplicationContext(), "ID del resumen: " + idResumen, Toast.LENGTH_SHORT).show();


                Intent x = new Intent(getApplicationContext()
                , ListarProductoActivity.class);
                x.putExtra("Id", idResumen);
                x.putExtra("Indice", position);
                startActivity(x);

                iniciarTemporizadorParaDialogo(idResumen, position);

            }
        });



    }

    ProductoDAO productoDAO = new ProductoDAO(this);

    private void iniciarTemporizadorParaDialogo(final int idResumen, final int position) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Si el usuario aún mantiene presionado el elemento después de 5 segundos,
                // muestra el diálogo de confirmación.
                mostrarDialogoConfirmacion(idResumen, position);
            }
        }, 5000); // 5000 milisegundos (5 segundos)
    }
    private void mostrarDialogoConfirmacion(final int idResumen, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_listarresumen.this);
        builder.setTitle("Eliminar elemento");
        builder.setMessage("¿Deseas eliminar este elemento?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Invoca la función de eliminación en tu DAO
                ResumenDAO dao = new ResumenDAO(activity_listarresumen.this);
                dao.Eliminar(idResumen);
                productoDAO.Eliminar(idResumen);
                Toast.makeText(getApplicationContext(), "Eliminado correctamente: " + position, Toast.LENGTH_SHORT).show();

                // Luego, actualiza tu adaptador o la lista y notifica cambios
                // Por ejemplo, si estás usando un ArrayAdapter, puedes llamar a
                // adapter.remove(resumenClicado); y luego adapter.notifyDataSetChanged();
                // o la implementación específica que tengas.
                // Esto dependerá de cómo estés manejando tus datos y adaptador.

                // Si estás usando un Intent para ir a otra actividad después de eliminar,
                // es posible que desees ajustar el código en consecuencia.

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    void MostrarResumen(){
        ResumenDAO dao = new ResumenDAO(this);
        ResumenAdapter adapter = new ResumenAdapter(
                this, R.layout.listadoresumen, dao.listar()

        );
        binding.ListResumen.setAdapter(adapter);

    }

    protected void onResume(){
        super.onResume();
        MostrarResumen();
    }
}