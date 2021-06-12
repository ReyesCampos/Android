package com.example.s_money

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.s_money.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*

class Tabs : AppCompatActivity() {

    lateinit var tabs:TabLayout
    lateinit var txtFecha:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        tabs = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        cargarIconos()
        txtFecha = findViewById(R.id.fecha)
        val date = SimpleDateFormat("MM-yyyy").format(Date())
        txtFecha.text = date

    }


    fun cargarIconos (){
        tabs.getTabAt(0)?.setIcon(R.drawable.ic_down)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_up)
    }
}