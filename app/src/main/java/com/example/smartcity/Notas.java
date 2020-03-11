package com.example.smartcity;

import org.w3c.dom.CDATASection;

import java.util.Date;

public class Notas {
    public String Titulo;
    public String Data;
    public String Local;


    public Notas(String name, String date, String local){
        this.Titulo = name;
        this.Data = date;
        this.Local = local;

    }

    public String getData() {
        return Data;
    }

    public String getLocal() {
        return Local;
    }

    public String getTitulo() {
        return Titulo;
    }
}


