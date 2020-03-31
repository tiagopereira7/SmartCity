package com.example.smartcity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "Notificacoes.db";


    public DB(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Contrato.Notas.SQL_CREATE_ENTRIES);
        db.execSQL(Contrato.Cidade.SQL_CREATE_ENTRIES);

        db.execSQL("insert into " + Contrato.Cidade.TABLE_NAME + " values (1, 'Porto');");
        db.execSQL("insert into " + Contrato.Cidade.TABLE_NAME + " values (2, 'Braga');");
        db.execSQL("insert into " + Contrato.Cidade.TABLE_NAME + " values (3, 'Viana do Castelo');");

        db.execSQL("insert into " + Contrato.Notas.TABLE_NAME + " values (4, 'Buraco na estrada', '20/01/2020', 2);");
        db.execSQL("insert into " + Contrato.Notas.TABLE_NAME + " values (5, 'Tampa Saneamento fora', '19/01/2020', 1);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Contrato.Notas.SQL_DROP_ENTRIES);
        db.execSQL(Contrato.Cidade.SQL_DROP_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    
    public  boolean addNotas(String titulo, String data, String local){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(Contrato.Notas.COLUMN_TITLE, titulo);
        cv.put(Contrato.Notas.COLUMN_DATA, data);
        cv.put(Contrato.Notas.COLUMN_ID_CIDADE, String.valueOf(local));

        long result = getWritableDatabase().insert(Contrato.Notas.TABLE_NAME, null, cv);
        return result != -1;
    }

    public  boolean UpdateNotas( int id, String titulo, String data, String local){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(Contrato.Notas.COLUMN_TITLE, titulo);
        cv.put(Contrato.Notas.COLUMN_DATA, data);
        cv.put(Contrato.Notas.COLUMN_ID_CIDADE, String.valueOf(local));

        long result = getWritableDatabase().update(Contrato.Notas.TABLE_NAME, cv, Contrato.Notas._ID + " = ?", new String[] {id +""});

        return result != -1;
    }


    public Cursor viewData(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "Select * from " + Contrato.Notas.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


}
