package com.example.smartcity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Notas.db";

    public DB(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Contrato.Notas.SQL_CREATE_ENTRIES);

        db.execSQL("insert into " + Contrato.Notas.TABLE_NAME + " values (1, 'Buraco na estrada', 20/01/20, 1);");
        db.execSQL("insert into " + Contrato.Notas.TABLE_NAME + " values (5, 'Carlos', 30, 3);");
        db.execSQL("insert into " + Contrato.Notas.TABLE_NAME + " values (6, 'Diogo', 10, 3);");
        db.execSQL("insert into " + Contrato.Notas.TABLE_NAME + " values (7, 'Fábio', 40, 3);");
        db.execSQL("insert into " + Contrato.Notas.TABLE_NAME + " values (8, 'Tiago', 50, 3);");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Contrato.Notas.SQL_DROP_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
