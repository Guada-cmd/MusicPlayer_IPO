package com.example.musicplayer_ipo.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer_ipo.Persistencia.ArtistaDAO;
import com.example.musicplayer_ipo.R;

public class ventana_detalles_artistas extends AppCompatActivity {

    private ImageView image_artista;
    private TextView nombre_artista;
    private TextView descripcion_artista;
    private Toast notification;

    private String identificador_artista;

    private ArtistaDAO gestor_artista = new ArtistaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_artistas);

        inicializarDatos();
        inicializarDatosBBDD();
        inicializarDescripciones();
        mostarNotificacion();

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar los datos de la ventana detalles artistas
     *
     */
    private void inicializarDatos(){

        this.nombre_artista = findViewById(R.id.lblTituloAlbum);
        this.image_artista = findViewById(R.id.ivAlbum);
        this.descripcion_artista = findViewById(R.id.lblDescripcionAlbum);

        Bundle bundle = getIntent().getExtras();
        this.identificador_artista = bundle.getString("identificador_artista");

    }

    /**
     *
     * Descripcion: Metodo que inicializa los datos realizando consultas en la base de datos
     *
     */
    private void inicializarDatosBBDD(){

        this.image_artista.setImageBitmap(gestor_artista.buscarImagenArtista(ventana_detalles_artistas.this, this.identificador_artista,
                "ImagenArtista"));

        this.nombre_artista.setText(gestor_artista.buscarDatosArtista(ventana_detalles_artistas.this, this.identificador_artista,
                "NombreArtista"));

    }

    /**
     *
     * Descripcion: Metodo que inicializa las descripciones de cada artista
     *
     */
    private void inicializarDescripciones(){

        switch(this.identificador_artista){
            case "1":
                this.descripcion_artista.setText("All Time Low es un grupo estadounidense de pop punk.\n" +
                        "Est?? conformado por Alex Gaskarth (voz y guitarra r??tmica),\n" +
                        "Jack Barakat (guitarra), Zack Merrick (bajo) y Rian Dawson (bater??a).\n" +
                        "El nombre de la banda fue tomado de la letra de una canci??n\n" +
                        "de New Found Glory.");
                break;
            case "2":
                this.descripcion_artista.setText("Tim Bergling m??s conocido por su nombre art??stico Avicii,\n" +
                        "fue un DJ y compositor sueco, especializado en\n" +
                        "programaci??n de audio, remezcla y producci??n de discos.");
                break;
            case "3":
                this.descripcion_artista.setText("Rebbeca Marie G??mez conocida art??sticamente como\n" +
                        "Becky G, es una cantante, compositora y actriz\n" +
                        "estadounidense.");
                break;
            case "4":
                this.descripcion_artista.setText("Edurne Garc??a Almagro m??s conocida como Edurne,\n" +
                        "es una cantante, compositora, modelo, actriz y\n" +
                        "presentadora espa??ola de televisi??n.");
                break;
            case "5":
                this.descripcion_artista.setText("Fifth Harmony fue un grupo musical femenino\n" +
                        "estadounidense que tuvo sus inicios en la segunda\n" +
                        "temporada del programa The X Factor en el 2012,\n" +
                        "donde obtuvieron el tercer lugar de la competencia.");
                break;
            case "6":
                this.descripcion_artista.setText("Harry Edward Styles es un cantante, compositor y\n" +
                        "actor brit??nico.");
                break;
            case "7":
                this.descripcion_artista.setText("Ram??n Melendi Espina, conocido art??sticamente\n" +
                        "como Melendi, es un cantautor y\n" +
                        "compositor espa??ol de m??sica pop y rumba.");
                break;
            case "8":
                this.descripcion_artista.setText("Queen es una banda brit??nica de rock formada\n" +
                        "en 1970 en Londres por el cantante y pianista\n" +
                        "Freddie Mercury, el guitarrista Brian May,\n" +
                        "el baterista Roger Taylor y el bajista John Deacon.");
                break;
            case "9":
                this.descripcion_artista.setText("Miguel Rafael Martos S??nchez, m??s conocido como\n" +
                        "Raphael, es un cantante y actor espa??ol, reconocido\n" +
                        "por ser uno de los precursores de la balada rom??ntica\n" +
                        "en Espa??a y en los pa??ses de habla hispana.");
                break;
            default:
                this.descripcion_artista.setText("Ninguna descripci??n disponible.");
        }
    }

    /**
     *
     * Descripcion: Metodo que muestra una notificacion
     *
     */
    private void mostarNotificacion(){

        notification = Toast.makeText(this, "Artista seleccionado: "
                + gestor_artista.buscarDatosArtista(ventana_detalles_artistas.this, this.identificador_artista,
                "NombreArtista"), Toast.LENGTH_LONG);
        notification.show();

    }
}