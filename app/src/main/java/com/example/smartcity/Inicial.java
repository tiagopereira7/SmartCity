package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Inicial extends AppCompatActivity {
    TextView name;
    Button btn_logout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_logout= (Button)findViewById(R.id.btn_logout);
        name = findViewById(R.id.emaillogin);

        Intent intent = getIntent();
        String extraname = intent.getStringExtra("Email");

        name.setText(extraname);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicial.this, MainActivity.class);
                startActivity(intent);
            }
        });
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


