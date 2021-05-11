package com.example.musicplayer_ipo.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.musicplayer_ipo.Constantes.Constantes;
import com.example.musicplayer_ipo.Dominio.Album;


public class AlbumDAO {

    /**
     *
     * Descipcion: Sirve para obtener la conexion (escritura)
     *
     * @param context
     * @return db
     */
    public SQLiteDatabase getConnWrite(Context context) {

        //Cada vez que se borre una tabla se cambia la version

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoIPO", null, 3);
        SQLiteDatabase db = conexion.getWritableDatabase();

        return db;

    }

    /**
     *
     * Descipcion: Sirve para obtener la conexion (lectura)
     *
     * @param context
     * @return db
     */
    public SQLiteDatabase getConnRead(Context context) {

        //Cada vez que se borre una tabla se cambia la version

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoIPO", null, 3);
        SQLiteDatabase db = conexion.getReadableDatabase();

        return db;

    }

    /**
     *
     * Descripcion: Metodo que permite realizar una consulta para obtener un determinado dato de un album
     * dado su clave primaria y el parametro que se desea obtener
     *
     * @param context
     * @param id_album
     * @param parametro
     * @return
     */
    public String buscarDatosArtista(Context context, String id_album, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = id_album;
        parametro_buscado [0] = parametro;

        String dato_buscado = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_ALBUM, parametro_buscado, Constantes.CAMPO_ALBUM_ID+"=?",
                    clave_primaria, null,null,null);

            cursor.moveToFirst();
            dato_buscado = cursor.getString(0);
            cursor.close();

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return dato_buscado;
    }

    /**
     *
     * Descripcion: Metodo que permite realizar una consulta para obtener un determinado dato de un album
     * dado su clave primaria y el nombre de la columna imagen en la base de datos
     *
     * @param context
     * @param id_album
     * @param parametro
     * @return
     */
    public Bitmap buscarImagenAlbum(Context context, String id_album, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];
        Bitmap bitmap = null;

        clave_primaria [0] = id_album;
        parametro_buscado [0] = parametro;

        byte [] image = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_ALBUM, parametro_buscado,
                    Constantes.CAMPO_ALBUM_ID+"=?",clave_primaria, null,null,null);

            cursor.moveToFirst();
            image = cursor.getBlob(0);
            cursor.close();

            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);


        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return bitmap;
    }

    /**
     *
     * Descripcion: Metodo que permite insertar un album en la tabla
     *
     * @param context
     * @param album
     * @param imagen
     */
    public void insertarDatosTablaAlbum(Context context, Album album, byte [] imagen){

        SQLiteDatabase db = this.getConnWrite(context);

        byte[] data = imagen;

        String sql = "INSERT INTO Album VALUES (?,?,?,?,?)";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, album.getIdAlbum());
        statement.bindString(2, album.getIdArtista());
        statement.bindString(3, album.getNombreAlbum());
        statement.bindString(4, album.getDuracionAlbum());
        statement.bindBlob(5, imagen);

        statement.executeInsert();

        db.close();

    }

    /**
     *
     * Descripcion: Metodo que permite actualizar una imagen del album
     *
     * @param context
     * @param id_album
     * @param image
     */
    public void updateDataImagenAlbum(Context context, String id_album, byte [] image) {

        SQLiteDatabase db = this.getConnWrite(context);

        String sql = "UPDATE Album SET ImagenAlbum = ? WHERE IdAlbum='"+id_album+"'";

        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindBlob(1, image);


        statement.execute();
        db.close();
    }

    /**
     *
     * Descripcion: Metodo que permite crear la tabla Album
     *
     * @param context
     */
    public void crearTablaAlbum(Context context){

        SQLiteDatabase db = this.getConnWrite(context);
        db.execSQL(Constantes.CREAR_TABLA_ALBUM);

    }

    /**
     *
     * Descripcion: Metodo que permite eleminar la tabla album
     *
     * @param context
     * @return
     */
    public int borrarTablaAlbum(Context context){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

        String borrar_tabla_artista_sql = "DROP TABLE Album";

        try{

            db.execSQL(borrar_tabla_artista_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        db.close();

        return resultado_consulta;
    }

    /**
     *
     * Descripcion: Metodo que permite obtener el numero total de albumes
     *
     * @param context
     * @return
     */
    public int getNumeroTotalAlbumes(Context context) {

        String countQuery = "SELECT  * FROM " + Constantes.NOMBRE_TABLA_ALBUM;
        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    /**
     *
     * Descripcion: Metodo que permite obtener los identificadores de todos los albumes de la base de
     * datos
     *
     * @param context
     * @param index
     * @return
     */
    public String [] getListaAlbumes(Context context, int index){

        String [] id_arlbumes = new String[index];
        int i = 0;

        String countQuery = "SELECT IdAlbum FROM " + Constantes.NOMBRE_TABLA_ALBUM;

        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            id_arlbumes[i] = cursor.getString(cursor.getColumnIndex("IdAlbum"));
            i = i + 1;

            cursor.moveToNext();
        }
        db.close();

        return id_arlbumes;

    }
}
