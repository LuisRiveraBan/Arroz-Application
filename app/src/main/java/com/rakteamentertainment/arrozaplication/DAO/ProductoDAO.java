package com.rakteamentertainment.arrozaplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rakteamentertainment.arrozaplication.Entidades.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoDAO {

    SQLiteDatabase db;
    DatabaseHelper helper;

    public ProductoDAO(Context context){helper = new DatabaseHelper(context);}



    public double CalculoSacos(List<Double> listaPesos, int sacos, double tara, double precio){


        double sumaPesos = 0.0;


        for (double peso : listaPesos) {
            sumaPesos += peso;
        }

        double resta = sumaPesos - (sacos * tara);

        double multiplicacion = resta * (precio / 50);

        return  multiplicacion;

    }

    public String GrabarProducto(Producto obj, List<Double> listaPesos){
        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        // Guardar los datos específicos de identificación del producto
        cv.put("id", obj.getId());
        cv.put("id_usuario", obj.getIdUsuario());

        // Iterar a través de la lista de pesos y guardar en las columnas 'linea1', 'linea2', ..., 'linea150'
        for (int numeroDeLinea = 1; numeroDeLinea <= listaPesos.size() && numeroDeLinea <= 150; numeroDeLinea++) {
            String nombreColumnaLinea = "linea" + numeroDeLinea;
            double valor = listaPesos.get(numeroDeLinea - 1); // Obtener el valor de la lista de pesos

            cv.put(nombreColumnaLinea, valor); // Insertar en la columna dinámica
        }

        // Insertar en la base de datos
        db.insert("producto", null, cv);
        db.close();

        return "El producto se ha registrado correctamente";
    }


    public String listarProductosPorId(int idProducto) {
        StringBuilder result = new StringBuilder();
        String cadSQL = "SELECT * FROM producto WHERE id ='" + idProducto + "'";
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(cadSQL, null);
        Log.d("DEBUG_TAG", "Query: " + cadSQL + ", idProducto: " + idProducto);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int idUsuario = cursor.getInt(1);

                double[] lineas = new double[150];
                List<Double> lineasNoCero = new ArrayList<>();

                for (int i = 0; i < 150; i++) {
                    lineas[i] = cursor.getDouble(i + 2);

                    // Agregar a la lista solo si el valor no es cero
                    if (lineas[i] != 0.0) {
                        lineasNoCero.add(lineas[i]);
                    }
                }

                // Si hay valores no cero, agregar los detalles a la cadena resultante
                if (!lineasNoCero.isEmpty()) {
                    result.append(String.format("ID: %d, ID Usuario: %d, Lineas: %s%n", id, idUsuario, lineasNoCero.toString()));
                }
            }
        }

        cursor.close();
        db.close();
        Log.d("DEBUG_TAG", "Lineas: "+ result.toString());
        return result.toString();
    }



    public List<Producto> listarProductosPorId2(int idProducto) {
        List<Producto> resultados = new ArrayList<>();
        String cadSQL = "SELECT * FROM producto WHERE id ='" + idProducto + "'";
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(cadSQL, null);
        Log.d("DEBUG_TAG", "Query: " + cadSQL + ", idProducto: " + idProducto);

        List<Double> lineasNoCero = null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int idUsuario = cursor.getInt(1);

                double[] lineas = new double[150];

                lineasNoCero = new ArrayList<>();

                for (int i = 0; i < 150; i++) {
                    lineas[i] = cursor.getDouble(i + 2);  // Index 2 corresponds to 'linea1'

                    // Agregar a la lista solo si el valor no es cero
                    if (lineas[i] != 0.0) {
                        lineasNoCero.add(lineas[i]);
                    }
                }

                // Si hay valores no cero, agregar los detalles a la lista intermedia
                if (!lineasNoCero.isEmpty()) {
                    Producto producto = new Producto(id, idUsuario, lineasNoCero.stream().mapToDouble(Double::doubleValue).toArray());
                    resultados.add(producto);
                }
            }
        }

        cursor.close();
        db.close();
        Log.d("DEBUG_TAG", "Lineas: " + lineasNoCero);
        return resultados;
    }



    public List<Producto> listarProductosPorId3() {
        List<Producto> resultados = new ArrayList<>();
        String cadSQL = "SELECT * FROM producto";
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(cadSQL, null);

        List<Double> lineasNoCero = null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int idUsuario = cursor.getInt(1);

                double[] lineas = new double[150];

                lineasNoCero = new ArrayList<>();

                for (int i = 0; i < 150; i++) {
                    lineas[i] = cursor.getDouble(i + 2);  // Index 2 corresponds to 'linea1'

                    // Agregar a la lista solo si el valor no es cero
                    if (lineas[i] != 0.0) {
                        lineasNoCero.add(lineas[i]);
                    }
                }

                // Si hay valores no cero, agregar los detalles a la lista intermedia
                if (!lineasNoCero.isEmpty()) {
                    Producto producto = new Producto(id, idUsuario, lineasNoCero.stream().mapToDouble(Double::doubleValue).toArray());
                    resultados.add(producto);
                }
            }
        }

        cursor.close();
        db.close();
        Log.d("DEBUG_TAG", "Lineas: " + lineasNoCero);
        return resultados;
    }


    public String Eliminar(int codigo) {
        db = helper.getWritableDatabase();

        // Utilizando parámetros en la consulta para evitar inyección de SQL
        db.delete("producto", "id=?", new String[]{String.valueOf(codigo)});

        db.close();

        return "El Usuario " + codigo + " Se Eliminó Correctamente";
    }




}
