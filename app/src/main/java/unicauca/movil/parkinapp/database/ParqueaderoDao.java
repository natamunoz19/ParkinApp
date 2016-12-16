package unicauca.movil.parkinapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.parkinapp.models.Parqueadero;

/**
 * Created by Natic_000 on 15/12/2016.
 */

public class ParqueaderoDao {

    static final String TABLE = "parqueadero";
    static final String C_ID = "id";
    static final String C_NAME = "nombre";
    static final String C_ADDRESS = "direccion";
    static final String C_PRICE = "precio";
    static final String C_LONG = "longitud";
    static final String C_LAT = "latitud";
    static final String C_QUALI = "calificacion";
    static final String C_QUANTI = "cantidad";
    static final String C_IMAGE = "imagen";
    static final String C_FREE = "lugaresLibres";
    static final String C_OPEN = "horarioApertura";
    static final String C_CLOSE = "horarioCerrado";
    static final String C_PHONE = "telefono";


    SQLiteDatabase db;

    public ParqueaderoDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Parqueadero p){
        ContentValues values = new ContentValues();
        values.put(C_ID, p.getId());
        values.put(C_NAME, p.getNombre());
        values.put(C_ADDRESS, p.getDireccion());
        values.put(C_PRICE, p.getPrecio());
        values.put(C_LONG, p.getLongitud());
        values.put(C_LAT, p.getLatitud());
        values.put(C_QUALI, p.getCalificacion());
        values.put(C_QUANTI, p.getCantidad());
        values.put(C_IMAGE, p.getImagen());
        values.put(C_FREE, p.getLugaresLibres());
        values.put(C_OPEN, p.getHorarioApertura());
        values.put(C_CLOSE, p.getHorarioCerrado());
        values.put(C_PHONE, p.getTelefono());

        db.insert(TABLE, null, values);
    }

    public void update(Parqueadero p){
        ContentValues values = new ContentValues();
        values.put(C_ID, p.getId());
        values.put(C_NAME, p.getNombre());
        values.put(C_ADDRESS, p.getDireccion());
        values.put(C_PRICE, p.getPrecio());
        values.put(C_LONG, p.getLongitud());
        values.put(C_LAT, p.getLatitud());
        values.put(C_QUALI, p.getCalificacion());
        values.put(C_QUANTI, p.getCantidad());
        values.put(C_IMAGE, p.getImagen());
        values.put(C_FREE, p.getLugaresLibres());
        values.put(C_OPEN, p.getHorarioApertura());
        values.put(C_CLOSE, p.getHorarioCerrado());
        values.put(C_PHONE, p.getTelefono());
        db.update(TABLE, values, "id = ?"
                , new String[]{""+p.getId()});
    }

    public void delete(long id){
        db.delete(TABLE,"id = ?", new String[]{""+id});
    }

    public Parqueadero getById(long id){
        String sql = "SELECT * FROM "+ TABLE+" WHERE id = "+id;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            cursor.moveToNext();
            return cursorToParqueadero(cursor);
        }else
            return null;
    }

    public List<Parqueadero> list(){
        String sql = "SELECT * FROM "+TABLE;
        return cursorToList(sql);
    }

    public List<Parqueadero> listByName(String name){
        String sql = "SELECT * FROM "+TABLE
                +" WHERE "+C_NAME+" LIKE '%"+name+"%'";
        return cursorToList(sql);
    }

    private Parqueadero cursorToParqueadero(Cursor cursor){
        Parqueadero p = new Parqueadero();
        p.setId(cursor.getLong(0));
        p.setNombre(cursor.getString(1));
        p.setDireccion(cursor.getString(2));
        p.setPrecio(cursor.getString(3));
        p.setLongitud(cursor.getString(4));
        p.setLatitud(cursor.getString(5));
        p.setCalificacion(cursor.getDouble(6));
        p.setCantidad(cursor.getLong(7));
        p.setImagen(cursor.getString(8));
        p.setLugaresLibres(cursor.getLong(9));
        p.setHorarioApertura(cursor.getString(10));
        p.setHorarioCerrado(cursor.getString(11));
        p.setTelefono(cursor.getString(12));

        return p;
    }

    private List<Parqueadero> cursorToList(String sql){
        Cursor cursor = db.rawQuery(sql, null);

        List<Parqueadero> data = new ArrayList<>();

        while (cursor.moveToNext()){
            Parqueadero p = cursorToParqueadero(cursor);
            data.add(p);
        }

        return data;
    }


}
