package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Inicial extends AppCompatActivity {
    TextView name;
    Button bt, btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bt= (Button)findViewById(R.id.bt_noti);
        name = findViewById(R.id.namelogin);

        Intent intent = getIntent();
        String extraname = intent.getStringExtra("name");

        name.setText(extraname);
    }

    public Inicial(){

    }

    public void noti(View v) {
        Intent i = new Intent(Inicial.this, notificacoes.class);
        startActivity(i);
    }

    public void mapa(View view) {
        Intent i = new Intent(Inicial.this, MapsActivity.class);
        startActivity(i);
    }
}


