package unicauca.movil.parkinapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.parkinapp.models.Parqueadero;
import unicauca.movil.parkinapp.models.Usuario;

/**
 * Created by Natic_000 on 12/12/2016.
 */

public class UsuarioDao {

    static final String TABLE = "usuario";
    static final String C_ID = "id";
    static final String C_NAME = "nombres";
    static final String C_LAST = "apellidos";
    static final String C_USER = "user";

    SQLiteDatabase db;

    public UsuarioDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Usuario u){
        ContentValues values = new ContentValues();
        values.put(C_ID, u.getId());
        values.put(C_NAME, u.getNombres());
        values.put(C_LAST, u.getApellidos());
        values.put(C_USER, u.getUser());

        db.insert(TABLE, null, values);
    }

    public void update(Usuario u){
        ContentValues values = new ContentValues();
        values.put(C_ID, u.getId());
        values.put(C_NAME, u.getNombres());
        values.put(C_LAST, u.getApellidos());
        values.put(C_USER, u.getUser());
        db.update(TABLE, values, "id = ?"
                , new String[]{""+u.getId()});
    }

    public void delete(long id){
        db.delete(TABLE,"id = ?", new String[]{""+id});
    }

    public void deleteAll(){

        db.delete(TABLE,null,null);
    }

    public Usuario getById(long id){
        String sql = "SELECT * FROM "+ TABLE+" WHERE id = "+id;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            cursor.moveToNext();
            return cursorToUsuario(cursor);
        }else
            return null;
    }

    public List<Usuario> list(){
        String sql = "SELECT * FROM "+TABLE;
        return cursorToList(sql);
    }

    public List<Usuario> listByName(String name){
        String sql = "SELECT * FROM "+TABLE
                +" WHERE "+C_NAME+" LIKE '%"+name+"%'";
        return cursorToList(sql);
    }

    private Usuario cursorToUsuario(Cursor cursor){
        Usuario u = new Usuario();
        u.setId(cursor.getLong(0));
        u.setNombres(cursor.getString(1));
        u.setApellidos(cursor.getString(2));
        u.setUser(cursor.getString(3));
        return u;
    }

    private List<Usuario> cursorToList(String sql){
        Cursor cursor = db.rawQuery(sql, null);

        List<Usuario> data = new ArrayList<>();

        while (cursor.moveToNext()){
            Usuario u = cursorToUsuario(cursor);
            data.add(u);
        }

        return data;
    }

}
