package com.example.smartcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Registar extends AppCompatActivity {
    private EditText editnomeRegisto, editEmailRegisto, editPasswordRegisto, editPasswordConfRegisto;
    private Button btnRegistar;
    private static String URL_REGIST = "https://192.168.1.65/myslim/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        editnomeRegisto = findViewById(R.id.nomeRegisto);
        editEmailRegisto = findViewById(R.id.emailRegisto);
        editPasswordRegisto = findViewById(R.id.passwordRegisto);
        editPasswordConfRegisto = findViewById(R.id.passwordConfRegisto);

        btnRegistar = findViewById(R.id.btnAdicionar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Regist(){
        final String nomeRegisto = this.editnomeRegisto.getText().toString().trim();
        final String EmailRegisto = this.editEmailRegisto.getText().toString().trim();
        final String PasswordRegisto = this.editPasswordRegisto.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(Registar.this, "Register Success!", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(Registar.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registar.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Nome", nomeRegisto);
                params.put("Email", EmailRegisto);
                params.put("password", PasswordRegisto);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
