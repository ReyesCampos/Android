package com.example.s_money

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.s_money.conexion.Conexion

class MainActivity : AppCompatActivity() {
    lateinit var username:EditText
    lateinit var pass:EditText
    lateinit var btnLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {

                startActivity(Intent(this, Tabs::class.java))

        }

    }

}