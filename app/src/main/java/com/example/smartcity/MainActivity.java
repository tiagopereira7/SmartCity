package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import static com.example.smartcity.R.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
    }

    public void Entrar(View v){
        Intent i = new Intent(MainActivity.this, Inicial.class);
        startActivity(i);
    }

}
