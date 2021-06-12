package com.example.covid

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.covid.databinding.ActivityTabsBinding
import com.example.covid.ui.main.SectionsPagerAdapter
import com.google.zxing.integration.android.IntentIntegrator

class Tabs : AppCompatActivity() {
    lateinit var tabs:TabLayout
    private lateinit var binding: ActivityTabsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setLogo(R.drawable.cora)

        binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener {
            initScaner()
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        tabs = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        cargarIconos()
    }

    private fun initScaner(){
        //IntentIntegrator(this).initiateScan()
        var integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanea el covid19 app para info")
        integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents == null){
                Toast.makeText(applicationContext, "Cancelado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "El valor escaneado es " + result.contents, Toast.LENGTH_LONG).show()
                if (result.contents.equals("http://itsncg.edu.mx")){

                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.camera_menu->{
                initScaner()
                Toast.makeText(applicationContext, "Hola", Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_dos->{
                Toast.makeText(applicationContext, "Hola2", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun cargarIconos (){
        tabs.getTabAt(0)?.setIcon(android.R.drawable.ic_dialog_dialer)
        tabs.getTabAt(1)?.setIcon(android.R.drawable.ic_dialog_map)
    }

}