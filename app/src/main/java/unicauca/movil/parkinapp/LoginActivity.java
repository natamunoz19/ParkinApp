package unicauca.movil.parkinapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import unicauca.movil.parkinapp.database.UsuarioDao;
import unicauca.movil.parkinapp.databinding.ActivityLoginBinding;
import unicauca.movil.parkinapp.models.Usuario;
import unicauca.movil.parkinapp.net.HttpAsyncTask;

public class LoginActivity extends AppCompatActivity implements HttpAsyncTask.OnResponseListener {

    //Context context = getApplicationContext();
    String usr,pass;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setHandler(this);

    }

    public void goToMain(){
        HttpAsyncTask task_POST = new HttpAsyncTask(HttpAsyncTask.POST, this);
        usr =  binding.usr.getEditText().getText().toString();
        pass =  binding.pass.getEditText().getText().toString();

        if (usr.isEmpty() || usr.equals("")){

            CharSequence text = "Digite su usuario";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else{
            if (pass.isEmpty() || pass.equals("")){
                CharSequence text = "Digite su contraseña";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }else {
                String obj = "{\"user\": \""+usr+"\", \"password\": \""+pass+"\"}";
                task_POST.execute(getString(R.string.url)+"usuarios/login", obj);
            }
        }
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            boolean success = (boolean) obj.get("success");

            if (success){
                JSONObject usu = obj.getJSONObject("user");
                Usuario u = new Usuario();
                u.setId(usu.getInt("id"));
                u.setNombres(usu.getString("nombres"));
                u.setApellidos(usu.getString("apellidos"));
                u.setUser(usu.getString("user"));
                UsuarioDao dao = new UsuarioDao(this);
                dao.insert(u);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                CharSequence text = "Datos errados. Validelos por favor";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
