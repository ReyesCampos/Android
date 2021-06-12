package com.example.covid.conexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Conexion (var contexto: Context) : SQLiteOpenHelper(contexto, "covid", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        var tablaFavoritos = "CREATE TABLE favoritos(id Integer not null primary key autoincrement," +
                "nombre varchar(100), imagen varchar(100))"
        db?.execSQL(tablaFavoritos)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}