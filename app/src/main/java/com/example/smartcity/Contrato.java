package com.example.smartcity;

import android.provider.BaseColumns;

public class Contrato {
    private static final String TEXT_TYPE = " TEXT ";
    private static final String INT_TYPE = " INTEGER ";


    public Contrato() {

    }


    public static abstract class Notas implements BaseColumns {
        public static final String TABLE_NAME = "Notas";
        public static final String COLUMN_TITLE = "Titulo";
        public static final String COLUMN_DATA = "Data";
        public static final String COLUMN_ID_CIDADE = "id_cidade";

        public static final String[] PROJECTION = {Notas._ID, Notas.COLUMN_TITLE, Notas.COLUMN_DATA, Notas.COLUMN_ID_CIDADE};


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Notas.TABLE_NAME + "(" +
                Notas._ID + INT_TYPE + " PRIMARY KEY, " +
                Notas.COLUMN_TITLE + TEXT_TYPE + "," +
                Notas.COLUMN_DATA + TEXT_TYPE + "," +
                Notas.COLUMN_ID_CIDADE + INT_TYPE + " REFERENCES " +
                Cidade.TABLE_NAME + "(" + Cidade._ID + "));";

        public static final String SQL_DROP_ENTRIES =
                "DROP TABLE " + Notas.TABLE_NAME + ";";
        
    }
    
    public static  abstract class Cidade implements BaseColumns {
        public static final String TABLE_NAME = "cidade";
        public static final String COLUMN_NOME = "nome";

        public static final String[] PROJECTION = {Cidade._ID, Cidade.COLUMN_NOME};

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Cidade.TABLE_NAME + "(" +
                Cidade._ID + INT_TYPE + " PRIMARY KEY, " +
                Cidade.COLUMN_NOME + TEXT_TYPE + ");";

        public static final String SQL_DROP_ENTRIES =
                "DROP TABLE " + Cidade.TABLE_NAME + ";";

    }

}
