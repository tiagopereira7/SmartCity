package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Registar extends AppCompatActivity {
    private EditText editnomeRegisto, editEmailRegisto, editPasswordRegisto, editPasswordConfRegisto;
    private Button btnAdicionar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        editnomeRegisto = findViewById(R.id.nomeRegisto);
        editEmailRegisto = findViewById(R.id.emailRegisto);
        editPasswordRegisto = findViewById(R.id.passwordRegisto);
        editPasswordConfRegisto = findViewById(R.id.passwordConfRegisto);

        btnAdicionar = findViewById(R.id.btnAdicionar);
    }
}
