package com.example.musicplayer_ipo.Adaptadores;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_ipo.Dominio.Cancion;
import com.example.musicplayer_ipo.Interfaz.OnItemSelectedListener;
import com.example.musicplayer_ipo.R;

import java.util.ArrayList;

public class AdaptadorListaPlayList extends RecyclerView.Adapter<AdaptadorListaPlayList.ViewHolder> {

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


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int posicion = getAdapterPosition();
                    if(itemSelectedListener != null){
                        itemSelectedListener.onArtistaSeleccionado(posicion);
                    }

                }
            });


            //Creación del menú popup

            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.getMenuInflater().inflate(R.menu.menu_contextual, popup.getMenu());
            view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override public void onCreateContextMenu(ContextMenu menu, View v,
                                                          ContextMenu.ContextMenuInfo menuInfo) {
                    popup.show();
                }
            });

            //Oyente de selección de opciones del menú popup

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                @Override public boolean onMenuItemClick(MenuItem item) {
                    if (itemSelectedListener != null) {
                        itemSelectedListener.onMenuContextualCancion(getAdapterPosition(), item);
                    }
                    return true;
                }
            });

        }
    }

    /**
     *
     * Descripcion: Constructor clase AdaptadorListaPlayList
     *
     * @param canciones
     */
    public AdaptadorListaPlayList(ArrayList<Cancion> canciones){

        this.canciones = canciones;

    }

    @Override
    public AdaptadorListaPlayList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_canciones, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorListaPlayList.ViewHolder holder, int position) {

        holder.lblNombre.setText(canciones.get(position).getNombreCancion());
        holder.lblDuracion.setText(canciones.get(position).getDuracionCancion());
        holder.imagCancion.setImageBitmap(canciones.get(position).getImagenCancion());

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

