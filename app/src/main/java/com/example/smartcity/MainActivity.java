package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.smartcity.R.id;
import static com.example.smartcity.R.layout;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText editEmail, editPassword;
    private TextView txtRegistar;
    private Button btn_Login;
    private static String URL_LOGIN = "http://comovsmartcity.000webhostapp.com/myslim/index.php/api/utilizadores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        txtRegistar = findViewById(id.registar);
        editEmail = findViewById(id.email);
        editPassword = findViewById(id.password);
        btn_Login = findViewById(R.id.btnLogin);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = editEmail.getText().toString().trim();
                String mPass = editPassword.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    Login(mEmail, mPass);

                } else {
                    editEmail.setError("Insert Email");
                    editPassword.setError("Insert Password");

                }
            }
        });


        txtRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(MainActivity.this, Registar.class);
                startActivity(regis);
            }
        });
    }

    private void Login(final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("Nome").trim();
                                    String email = object.getString("Email").trim();

                                    Intent intent = new Intent(MainActivity.this, Inicial.class);
                                    intent.putExtra("Nome", name);
                                    startActivity(intent);

                                    Toast.makeText(MainActivity.this,
                                            "Success Login. \n Your Name: "
                                            +name+ "\nYour Email: " +email, 
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error!" +e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error!" +error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Email", email);
                params.put("password", password);
                return  params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    public void Entrar(View v){
            //username = editEmail.getText().toString();
            //password = editPassword.getText().toString();

            Intent i = new Intent(MainActivity.this, Inicial.class);
            startActivity(i);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    }

