package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.smartcity.R.id;
import static com.example.smartcity.R.layout;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText editUserName, editPassword;
    private TextView txtRegistar;
    private String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        txtRegistar = findViewById(id.registar);
        editUserName = findViewById(id.username);
        editPassword = findViewById(id.password);

        txtRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(MainActivity.this, Registar.class);
                startActivity(regis);
            }
        });
    }

    public void Entrar(View v){
            username = editUserName.getText().toString();
            password = editPassword.getText().toString();


            Intent i = new Intent(MainActivity.this, Inicial.class);
            startActivity(i);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    }

