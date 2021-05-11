package com.example.musicplayer_ipo.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_ipo.Dominio.Cancion;
import com.example.musicplayer_ipo.Interfaz.OnItemSelectedListener;
import com.example.musicplayer_ipo.R;

import java.util.ArrayList;

public class AdaptadorListaCancion extends RecyclerView.Adapter<AdaptadorListaCancion.ViewHolder> {

    private ArrayList<Cancion> canciones;
    private OnItemSelectedListener itemSelectedListener;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombre;
        private TextView lblDuracion;
        private ImageView imagCancion;

        ViewHolder(View view) {

            super(view);

            lblNombre = view.findViewById(R.id.lblNombreArtista);
            lblDuracion = view.findViewById(R.id.lblGeneroArtista);
            imagCancion = view.findViewById(R.id.imgArtista);

        }
    }

    /**
     *
     * Descripcion: Constructor clase AdaptadorListaCancion
     *
     * @param canciones
     */
    public AdaptadorListaCancion(ArrayList<Cancion> canciones){

        this.canciones = canciones;

    }

    @Override
    public AdaptadorListaCancion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_canciones, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorListaCancion.ViewHolder holder, int position) {

        holder.lblNombre.setText(canciones.get(position).getNombreCancion());
        holder.lblDuracion.setText(canciones.get(position).getDuracionCancion());
        holder.imagCancion.setImageBitmap(canciones.get(position).getImagenCancion());

        /**
         *
         * Descripcion: Oyente asociado al pulsar un item de la lista
         *
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.lblNombre.getText().toString() != "No disponible" ||
                        canciones.get(position).getImagenCancion() != null){

                    //Intent reproducir_audio = new Intent(holder.itemView.getContext(), ventana_reproducir.class);
                    //reproducir_audio.putExtra("identificador_cancion", canciones.get(position).getIdCancion());
                    //holder.itemView.getContext().startActivity(reproducir_audio);

                }
            }
        });
    }

    /**
     *
     * @param itemSelectedListener
     */
    public void setItemSelectedListener(OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }
}
