package unicauca.movil.parkinapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natic_000 on 12/12/2016.
 */

public class CalificacionesDao {

    static final String TABLE = "calificacion";
    static final String C_IDUSER = "idUsuario";
    static final String C_IDPARQ = "idParqueadero";
    static final String C_CALIF = "calificacion";

    SQLiteDatabase db;

    public CalificacionesDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(long idU,long idP, double calif){
        ContentValues values = new ContentValues();
        values.put(C_IDUSER, idU);
        values.put(C_IDPARQ, idP);
        values.put(C_CALIF, calif);

        db.insert(TABLE, null, values);
    }

    public void delete(long id){

        db.delete(TABLE,"idParqueadero = ?", new String[]{""+id});
    }

    public void deleteAll(){

        db.delete(TABLE,null,null);
    }

    public List<String> list(){
        String sql = "SELECT * FROM "+TABLE;
        return cursorToList(sql);
    }

    public List<String> listCalif(){
        String sql = "SELECT * FROM "+TABLE;
        return cursorToListCalif(sql);
    }

    private List<String> cursorToList(String sql){
        Cursor cursor = db.rawQuery(sql, null);

        List<String> data = new ArrayList<>();

        while (cursor.moveToNext()){
            String u = String.valueOf(cursor.getLong(2));
            data.add(u);
        }

        return data;
    }

    private List<String> cursorToListCalif(String sql){
        Cursor cursor = db.rawQuery(sql, null);

        List<String> data = new ArrayList<>();

        while (cursor.moveToNext()){
            String u = String.valueOf(cursor.getLong(3));
            data.add(u);
        }

        return data;
    }

    public double getById(long id){
        String sql = "SELECT * FROM "+ TABLE+" WHERE idParqueadero = "+id;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            return cursor.getDouble(3);
        }else
            return 100;
    }

}
