package com.rakteamentertainment.arrozaplication.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.rakteamentertainment.arrozaplication.Entidades.Producto;
import com.rakteamentertainment.arrozaplication.R;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private Context miContext;
    private int milayout;
    private List<Producto> miLista;
    private int startIndex;

    public ProductoAdapter(Context context, int resource, List<Producto> objects) {
        miContext = context;
        milayout = resource;
        miLista = objects;
        startIndex = 0;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(milayout, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        int index = position + startIndex;

        if (index < miLista.size()) {
            Producto obj = miLista.get(index);
            holder.bindData(obj);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }




    @Override
    public int getItemCount() {
        return (int) Math.ceil((double) miLista.size() / 10);
    }



    public class ProductoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private LinearLayout linearLayout;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            linearLayout = (LinearLayout) itemView;
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }

        public void bindData(Producto obj) {
            Integer id = obj.getId();
            String idString = String.valueOf(id);

            TextView textViewId = new TextView(miContext);
            textViewId.setText("ID: " + idString);
            textViewId.setTextSize(18);

            LinearLayout.LayoutParams layoutParamsId = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParamsId.setMargins(0, 0, 0, 10);
            textViewId.setLayoutParams(layoutParamsId);

            linearLayout.addView(textViewId);

            double[] lineaCompleta = obj.getLinea();
            double sumaGrupo = 0; // Inicializamos la suma del grupo

            for (int i = 0; i < lineaCompleta.length; i++) {
                int pesoIndex = (i % 10) + 1;
                sumaGrupo += lineaCompleta[i]; // Sumamos cada número al grupo

                TextView textViewLinea = new TextView(miContext);

                if (pesoIndex == 10 || i == lineaCompleta.length - 1) {
                    // Si el peso es 10, mostrar la suma del grupo y aplicar un margen más grande
                    textViewLinea.setText("Peso " + pesoIndex + ": " + lineaCompleta[i] + " -| Suma: " + sumaGrupo );
                    textViewLinea.setTextSize(16);
                    textViewLinea.setTextSize(16);
                    LinearLayout.LayoutParams layoutParamsLinea = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    layoutParamsLinea.setMargins(0, 0, 0, 50);
                    textViewLinea.setLayoutParams(layoutParamsLinea);


                    // Reiniciar la suma para el próximo grupo
                    sumaGrupo = 0;
                }

                else {
                    textViewLinea.setText("Peso " + pesoIndex + ": " + lineaCompleta[i]);
                    textViewLinea.setTextSize(16);
                    LinearLayout.LayoutParams layoutParamsLinea = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    layoutParamsLinea.setMargins(0, 0, 0, 10);
                    textViewLinea.setLayoutParams(layoutParamsLinea);
                }

                linearLayout.addView(textViewLinea);
            }
        }




    }
}


