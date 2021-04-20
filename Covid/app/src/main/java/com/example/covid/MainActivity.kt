package com.example.covid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var txtUsuario: EditText;
    //lateinit var txtPassword: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtUsuario=findViewById(R.id.usuario);
        var txtPassword: EditText = findViewById(R.id.pass)
        Log.e("Prueba", txtUsuario.text.toString())
        print(txtPassword.text)
        login(txtUsuario.text.toString(), txtPassword.text.toString())
    }

    fun login (user:String, pass:String, isAdmin:Boolean=true, id:Int=-1){
        if (user.equals("admin") && pass.equals("123")){
            var intent =Intent(this, Principal::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext, "Error, credenciales invalidas", Toast.LENGTH_LONG).show()
        }
    }

}