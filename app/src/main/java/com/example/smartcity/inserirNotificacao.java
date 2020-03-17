package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inserirNotificacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_notificacao);


    }

    public void adicionar(View v) {

        Intent output = new Intent();
        setResult(RESULT_OK,output);
        EditText editNome = (EditText) findViewById(R.id.editTitulo);
        EditText editLocal = (EditText) findViewById(R.id.editLocal);

        if (editNome.getText().toString().equals("")){
            Toast.makeText(inserirNotificacao.this, "Preencha o campo titulo ", Toast.LENGTH_LONG).show();
        }
        if (editLocal.getText().toString().equals("")){
            Toast.makeText(inserirNotificacao.this, "Preencha o campo Local ", Toast.LENGTH_LONG).show();
        }
        finish();

    }
}
