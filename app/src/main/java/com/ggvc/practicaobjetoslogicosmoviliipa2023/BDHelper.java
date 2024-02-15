package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREACIÓN DE LAS TABLAS
            db.execSQL("CREATE TABLE tbRegVehiculos"+"(" +
                    "cedula_usu INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "nombre INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "propietario text NOT NULL,"+
                    "anFabricacion text NOT NULL," +
                    "marca text NOT NULL,"+
                    "color text NOT NULL)"
            );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //CAMBIE LA VERSIÓN DE LA TABLA DE LA BDD
        db.execSQL("DROP TABLE tblUsuarios");
        onCreate(db);
    }
}
