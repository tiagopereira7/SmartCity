package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import static com.example.smartcity.R.*;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText editEmail, editPassword;
    private TextView txtRegistar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        txtRegistar = findViewById(id.registar);
        txtRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(MainActivity.this, Registar.class);
                startActivity(regis);
            }
        });
    }

    public void Entrar(View v){
        Intent i = new Intent(MainActivity.this, Inicial.class);
        startActivity(i);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
