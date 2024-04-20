package com.rakteamentertainment.arrozaplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.rakteamentertainment.arrozaplication.Adaptadores.ProductoAdapter;
import com.rakteamentertainment.arrozaplication.Adaptadores.ResumenAdapter;
import com.rakteamentertainment.arrozaplication.Adaptadores.ResumenAdapter2;
import com.rakteamentertainment.arrozaplication.DAO.ProductoDAO;
import com.rakteamentertainment.arrozaplication.DAO.ResumenDAO;
import com.rakteamentertainment.arrozaplication.Entidades.Producto;
import com.rakteamentertainment.arrozaplication.R;
import com.rakteamentertainment.arrozaplication.databinding.ActivityListarProductoBinding;
import com.rakteamentertainment.arrozaplication.databinding.ActivityProductoactivity2Binding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListarProductoActivity extends AppCompatActivity {

    private ActivityListarProductoBinding binding;
    private ProductoAdapter adapter;
    private ProductoDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MostrarProducto2();
        initRecyclerView();
        MostrarResumen();
    }

    void initRecyclerView() {
        adapter.setOnItemClickListener(new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ProductoDAO dao = new ProductoDAO(ListarProductoActivity.this);
                List<Producto> productos = dao.listarProductosPorId3();

                if (productos != null && position < productos.size()) {
                    Producto productoClicado = productos.get(position);

                    // Ahora puedes acceder al ID del producto
                    int idProducto = productoClicado.getId();

                    // Haz lo que necesites con el ID
                    // Por ejemplo, muestra el ID en un Toast
                    Toast.makeText(getApplicationContext(), "ID del producto: " + idProducto, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "NULO: " , Toast.LENGTH_SHORT).show();
                    // Manejar el caso en que productos sea null o la posición esté fuera del rango
                    // Puedes mostrar un mensaje de error o realizar alguna acción adecuada.
                }
            }
        });
    }
/*
    void MostrarProducto(){
        Intent x = getIntent();
        int indice = x.getIntExtra("Indice", 0);
        ProductoDAO dao = new ProductoDAO(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String >(
                this, android.R.layout.simple_list_item_1, Collections.singletonList(dao.listarProductosPorId(1001))
        );
        binding.lvProductoPesos.setAdapter(adapter);

    };
*/
    void MostrarProducto2() {
        Intent x = getIntent();
        int indice = x.getIntExtra("Indice", 0);
        int idresumen = x.getIntExtra("Id",0);
        ProductoDAO dao = new ProductoDAO(this);

        // Cambiamos la creación del adaptador para usar un layout simple sin elementos adicionales
         adapter = new ProductoAdapter(this, R.layout.listarproductoactivity, dao.listarProductosPorId2(idresumen));
        RecyclerView recyclerView = findViewById(R.id.lvProductoPesos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    void MostrarResumen(){
        Intent x = getIntent();
        int idresumen = x.getIntExtra("Id",0);
        ResumenDAO dao = new ResumenDAO(this);
        ResumenAdapter2 adapter = new ResumenAdapter2(
                this, R.layout.listadoresumen2, dao.listarBuscar(idresumen)

        );
        binding.lvResumenProducto.setAdapter(adapter);

    }


}