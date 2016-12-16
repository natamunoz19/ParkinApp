package unicauca.movil.parkinapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import unicauca.movil.parkinapp.databinding.ActivityLoginBinding;
import unicauca.movil.parkinapp.net.HttpAsyncTask;

public class LoginActivity extends AppCompatActivity implements HttpAsyncTask.OnResponseListener {

    //Context context = getApplicationContext();
    ActivityLoginBinding binding;
    HttpAsyncTask task_GET = new HttpAsyncTask(HttpAsyncTask.GET, this);

    HttpAsyncTask task_POST = new HttpAsyncTask(HttpAsyncTask.POST, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setHandler(this);

    }

    public void goToMain(){
        String usr =  binding.usr.getEditText().getText().toString();
        String pass =  binding.pass.getEditText().getText().toString();

        if (usr == null){

            CharSequence text = "Digite su usuario";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else{
            if (pass == null){
                CharSequence text = "Digite su contraseña";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }else {
                //task_GET.execute(getString(R.string.url));
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        Log.i("Restaurante", "Usr:"+usr+" Pass:"+pass);
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            boolean success = obj.getBoolean("success");
            if (success){

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
