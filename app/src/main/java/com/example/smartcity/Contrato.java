package com.example.smartcity;

import android.provider.BaseColumns;

import java.util.Date;

public class Contrato {
    private static final String TEXT_TYPE = " TEXT ";
    private static final String INT_TYPE = " INTEGER ";


    public Contrato()

    {

    }


    public static abstract class Notas implements BaseColumns {
        public static final String TABLE_NAME = "Notas";
        public static final String COLUMN_TITULO = "Titulo";
        public static final String COLUMN_DATA = "Data";
        public static final String COLUMN_LOCAL = "Local";

        public static final String[] PROJECTION = {Notas._ID, Notas.COLUMN_TITULO, Notas.COLUMN_DATA, Notas.COLUMN_LOCAL};


        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Notas.TABLE_NAME + "(" + Notas._ID + INT_TYPE + " PRIMARY KEY," + Notas.COLUMN_TITULO + TEXT_TYPE + "," +
            Notas.COLUMN_DATA + INT_TYPE + "," + Notas.COLUMN_LOCAL + TEXT_TYPE + "," ;

        public static final String SQL_DROP_ENTRIES =
                "DROP TABLE " + Notas.TABLE_NAME + ";";
    }

}
