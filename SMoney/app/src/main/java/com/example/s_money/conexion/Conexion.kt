package com.example.s_money.conexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Conexion(var contexto: Context) : SQLiteOpenHelper(contexto, "Transacciones", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        var tablaGastos = "CREATE TABLE gastos(id Integer not null primary key autoincrement," +
                "nombre varchar(100), monto double)"
        db?.execSQL(tablaGastos)
        var tablaIngresos = "CREATE TABLE ingresos(id Integer not null primary key autoincrement," +
                "nombre varchar(100), monto double)"
        db?.execSQL(tablaIngresos)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}