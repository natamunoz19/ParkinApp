package unicauca.movil.parkinapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import unicauca.movil.parkinapp.database.CalificacionesDao;
import unicauca.movil.parkinapp.database.ParqueaderoDao;
import unicauca.movil.parkinapp.database.PreferenciasDao;
import unicauca.movil.parkinapp.database.UsuarioDao;
import unicauca.movil.parkinapp.databinding.ActivityMainBinding;
import unicauca.movil.parkinapp.fragments.TabPagerAdapter;
import unicauca.movil.parkinapp.models.Parqueadero;
import unicauca.movil.parkinapp.models.Usuario;
import unicauca.movil.parkinapp.net.HttpAsyncTask;

/**
 * Created by Natic_000 on 13/12/2016.
 */

public class MainActivity extends AppCompatActivity implements HttpAsyncTask.OnResponseListener {

    //Context context = getApplicationContext();
    ActivityMainBinding binding;
    int process=0;

    UsuarioDao usuDao = new UsuarioDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Ubicar parqueaderos"));
        tabLayout.addTab(tabLayout.newTab().setText("Lista de parqueaderos"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });


        loadParqeaderos();
        loadPreferencias();
        loadCalificaciones();


    }

    private void loadParqeaderos() {
        HttpAsyncTask task_GET = new HttpAsyncTask(HttpAsyncTask.GET, this);
        process = 1;
        task_GET.execute(getString(R.string.url)+"parqueaderos/calif");

    }

    private void loadPreferencias() {

        List<Usuario> user = usuDao.list();
        Usuario usua = user.get(0);
        String obj = "{\"id\":"+usua.getId()+"}";
        HttpAsyncTask task_POST = new HttpAsyncTask(HttpAsyncTask.POST, this);
        process = 2;
        task_POST.execute(getString(R.string.url)+"parqueaderos/fav", obj);

    }

    private void loadCalificaciones() {
        List<Usuario> user = usuDao.list();
        Usuario usua = user.get(0);
        String obj = "{\"id\":"+usua.getId()+"}";
        HttpAsyncTask task_POST = new HttpAsyncTask(HttpAsyncTask.POST, this);
        process = 3;
        task_POST.execute(getString(R.string.url)+"parqueaderos/califAll", obj);
    }


    @Override
    public void onResponse(String response) {

        switch (process){
            case 1:
                process1(response);
            case 2:
                process2(response);
            case 3:
                process3(response);
        }

    }

    private void process3(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            boolean success = (boolean) obj.get("success");

            if (success){
                CalificacionesDao dao = new CalificacionesDao(this);
                JSONArray parq = obj.getJSONArray("parq");
                Long id_user,id_parq;
                Double calif;
                for (int i=0; i<parq.length(); i++){
                    JSONObject parque = parq.getJSONObject(i);
                    id_user = parque.getLong("idUsuario");
                    id_parq = parque.getLong("idParqueadero");
                    calif = parque.getDouble("calificacion");
                    dao.insert(id_user,id_parq,calif);
                }
            }else{
                Log.i("Error", "No success loadCalificaciones");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void process2(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            boolean success = (boolean) obj.get("success");

            if (success){
                PreferenciasDao dao = new PreferenciasDao(this);
                JSONArray parq = obj.getJSONArray("parq");
                Long id_user,id_parq;
                for (int i=0; i<parq.length(); i++){
                    JSONObject parque = parq.getJSONObject(i);
                    id_user = parque.getLong("id_usuario");
                    id_parq = parque.getLong("id_parqueadero");
                    dao.insert(id_user,id_parq);
                }
            }else{
                Log.i("Error", "No success loadPreferencias");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void process1(String r) {
        try {
            JSONObject obj = new JSONObject(r);
            boolean success = (boolean) obj.get("success");

            if (success){
                ParqueaderoDao dao = new ParqueaderoDao(this);
                JSONArray parq = obj.getJSONArray("parq");
                for (int i=0; i<parq.length(); i++){
                    JSONObject parque = parq.getJSONObject(i);
                    Parqueadero p = new Parqueadero();
                    p.setId(parque.getLong("id"));
                    p.setNombre(parque.getString("nombre"));
                    p.setDireccion(parque.getString("direccion"));
                    p.setPrecio(parque.getString("precio"));
                    p.setLongitud(parque.getString("longitud"));
                    p.setLatitud(parque.getString("latitud"));
                    p.setCalificacion(Double.parseDouble(parque.getString("calificacion")));
                    p.setCantidad(Long.parseLong(parque.getString("cantidad")));
                    p.setImagen(parque.getString("imagen"));
                    p.setLugaresLibres(Long.parseLong(parque.getString("lugaresLibres")));
                    p.setHorarioApertura(parque.getString("horarioApertura"));
                    p.setHorarioCerrado(parque.getString("horarioCerrado"));
                    p.setTelefono(parque.getString("telefono"));

                    dao.insert(p);
                }

            }else{
                Log.i("Error", "No success loadParqueaderos");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
