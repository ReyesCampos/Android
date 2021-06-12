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
        username = findViewById(R.id.nickname)
        pass = findViewById(R.id.pass)
        btnLogin = findViewById(R.id.btnLogin)

        login(username.text.toString(), pass.text.toString())
    }

    fun login(user:String, pass:String){

            btnLogin.setOnClickListener { startActivity(Intent(this, Tabs::class.java)) }

    }

}