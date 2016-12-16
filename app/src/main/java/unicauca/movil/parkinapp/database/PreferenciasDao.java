package unicauca.movil.parkinapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.parkinapp.models.Usuario;

/**
 * Created by Natic_000 on 12/12/2016.
 */

public class PreferenciasDao {

    static final String TABLE = "preferencias";
    static final String C_IDUSER = "id_usuario";
    static final String C_IDPARQ = "id_parqueadero";

    SQLiteDatabase db;

    public PreferenciasDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(long idU,long idP){
        ContentValues values = new ContentValues();
        values.put(C_IDUSER, idU);
        values.put(C_IDPARQ, idP);

        db.insert(TABLE, null, values);
    }

    public void delete(long id){

        db.delete(TABLE,"id_parqueadero = ?", new String[]{""+id});
    }

    public void deleteAll(){

        db.delete(TABLE,null,null);
    }

    public List<String> list(){
        String sql = "SELECT * FROM "+TABLE;
        return cursorToList(sql);
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

    public boolean getById(long id){
        String sql = "SELECT * FROM "+ TABLE+" WHERE id_parqueadero = "+id;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            return true;
        }else
            return false;
    }

}
