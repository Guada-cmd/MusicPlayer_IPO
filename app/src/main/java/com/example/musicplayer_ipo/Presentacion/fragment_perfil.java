package com.example.musicplayer_ipo.Presentacion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.musicplayer_ipo.Dominio.Usuario;
import com.example.musicplayer_ipo.R;


/**
 *
 */
public class fragment_perfil extends Fragment {

    private Usuario usuario;

    private TextView lblNombre_usuario_perfil_BBDD;
    private TextView nombrePerfilTitulo;
    private TextView CorreoPerfilTitulo;
    private TextView TelefonoPerfilTitulo;
    private TextView FechaNacimientoPerfilTitulo;
    private ImageView image_foto_perfil;

    public fragment_perfil(Usuario usuario) {

        this.usuario = usuario;

    }
    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */

    boolean mCalled;
    android.app.Fragment mHost;

    public void onAttach(Context context) {
        super.onAttach(context);
        mCalled = true;
        final Activity hostActivity = mHost == null ? null : mHost.getActivity();
        if (hostActivity != null) {
            mCalled = false;
            onAttach(hostActivity);
        }
    }

    /**
     * @deprecated Use {@link #onAttach(Context)} instead.
     */
    @Deprecated
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCalled = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        inicializarDatos(view);
        mostrarDatos();

        return view;

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar los datos de la ventana reproductor
     *
     */
    private void inicializarDatos(View view){

        lblNombre_usuario_perfil_BBDD = (TextView) view.findViewById(R.id.lblNombreUsuario);

        lblNombre_usuario_perfil_BBDD = (TextView) view.findViewById(R.id.lblNombreUsuario);
        nombrePerfilTitulo = (TextView) view.findViewById(R.id.nombrePerfilTitulo);
        CorreoPerfilTitulo = (TextView) view.findViewById(R.id.CorreoPerfilTitulo);
        TelefonoPerfilTitulo = (TextView) view.findViewById(R.id.TelefonoPerfilTitulo);
        FechaNacimientoPerfilTitulo = (TextView) view.findViewById(R.id.FechaNacimientoPerfilTitulo);
        image_foto_perfil =  (ImageView) view.findViewById(R.id.imageView_foto_perfil);

    }

    /**
     *
     * Descripcion: Metodo que permite mostart los datos despues de hacer las consultas a la base de datos
     *
     */
    private void mostrarDatos(){

        lblNombre_usuario_perfil_BBDD.setText(this.usuario.getNombreUsuario());
        nombrePerfilTitulo.setText("Nombre: "+this.usuario.getNombre());
        TelefonoPerfilTitulo.setText("Telefono: "+this.usuario.getTelefono());
        CorreoPerfilTitulo.setText("Correo: "+this.usuario.getCorreo());
        FechaNacimientoPerfilTitulo.setText("F. Nacimiento: "+this.usuario.getFechaNacimiento());

        image_foto_perfil.setImageBitmap(this.usuario.getFotoPerfil());

    }

}