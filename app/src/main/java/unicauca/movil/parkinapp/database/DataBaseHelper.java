package unicauca.movil.parkinapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;

/**
 * Created by Natic_000 on 15/12/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "parkinapp.db";
    static int VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUsuario = "CREATE TABLE usuario (id INTEGER PRIMARY KEY"
                +", nombres VARCHAR"
                +", apellidos VARCHAR"
                +", user VARCHAR"
                +" )";
        sqLiteDatabase.execSQL(sqlUsuario);

        String sqlParqueadero = "CREATE TABLE parqueadero (id INTEGER PRIMARY KEY"
                +", nombre VARCHAR"
                +", direccion VARCHAR"
                +", precio VARCHAR"
                +", longitud VARCHAR"
                +", latitud VARCHAR"
                +", calificacion DOUBLE"
                +", cantidad INT"
                +", imagen VARCHAR"
                +", lugaresLibres INT"
                +", horarioApertura VARCHAR"
                +", horarioCerrado VARCHAR"
                +", telefono VARCHAR"
                +" )";
        sqLiteDatabase.execSQL(sqlParqueadero);

        String sqlPreferencias = "CREATE TABLE preferencias (id INTEGER PRIMARY KEY AUTOINCREMENT"
                +", id_usuario INT"
                +", id_parqueadero INT"
                +" )";
        sqLiteDatabase.execSQL(sqlPreferencias);

        String sqlCalificacion = "CREATE TABLE calificacion (id INTEGER PRIMARY KEY"
                +", idUsuario INT"
                +", idParqueadero INT"
                +", calificacion INT"
                +" )";
        sqLiteDatabase.execSQL(sqlCalificacion);


        /*Insertamos un registro en la base de datos
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", "Estrella de Muerte I");
        contentValues.put("gravedad", SensorManager.GRAVITY_DEATH_STAR_I);

        sqLiteDatabase.insert("planeta",null, contentValues);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE usuario");
        sqLiteDatabase.execSQL("DROP TABLE parqueadero");
        sqLiteDatabase.execSQL("DROP TABLE preferencias");
        sqLiteDatabase.execSQL("DROP TABLE calificacion");
        onCreate(sqLiteDatabase);
    }
}
