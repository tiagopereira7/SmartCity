package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Inicial extends AppCompatActivity {
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bt= (Button)findViewById(R.id.bt_noti);

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


