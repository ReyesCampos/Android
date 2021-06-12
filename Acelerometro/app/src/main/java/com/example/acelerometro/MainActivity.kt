package com.example.acelerometro

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var mAcce: Sensor
    private lateinit var txtCoor: TextView
    private lateinit var pacman: ImageButton
    private var lastUpdate: Long = 0
    private var screenWidth = 0
    private var screenHeight = 0
    private var cuantos:Int = 0
    private var arreglo = arrayListOf<ImageButton>()
    private lateinit var padre:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAcce = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        txtCoor = findViewById(R.id.txtCoor)
        pacman = findViewById(R.id.pacman)
        padre = findViewById(R.id.padre)
        var display = windowManager.defaultDisplay
        var point = Point()
        display.getSize(point)
        screenWidth = point.x
        screenHeight = point.y
        pacman.setOnLongClickListener {
            cuantos = (6..20).random()
            generar()
            pacman.bringToFront()
            return@setOnLongClickListener true
        }
    }

    fun generar (){
        var x = screenWidth / cuantos
        var y = screenHeight / cuantos
        for (i in 0..cuantos){
            for (j in 0..cuantos){
                var button = ImageButton(this)
                button.layoutParams = ViewGroup.LayoutParams(x,y)
                button.x = (j*x).toFloat()
                button.y = (i*y).toFloat()
                button.id = x
                button.setImageResource(R.drawable.like)
                button.setBackground(null)
                arreglo.add(button)
                padre.addView(button)
            }//for j
        }//for i
    }

    override fun onPostResume() {
        super.onPostResume()
        sensorManager!!.registerListener(this, mAcce, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null){
            var sensor = event.sensor
            if (sensor.type == Sensor.TYPE_ACCELEROMETER){
                var tiempo:Long = System.currentTimeMillis()
                if ((tiempo - lastUpdate) > 100 && arreglo.size>0){
                    lastUpdate = tiempo-lastUpdate
                    var x = event.values[0]
                    var y = event.values[1]
                    var z = event.values[2]
                    Log.e("Valor X", x.toString())
                    Log.e("Valor Y", y.toString())
                    Log.e("Valor Z", z.toString())
                    var centerX = screenWidth/2
                    var centerY = screenHeight/2
                    var relX = screenWidth/20
                    val relY = screenHeight/20

                    //MOVER EN HORIZONTAL
                    if(x > 0){
                        var x = centerX - (relX * (x*-1) - 75)
                        pacman.x = x
                    }else{
                        pacman.x = (relX * x) - 75 + centerX
                    }
                    //MOVER EN VERTICAL
                    if(y > 0){
                        pacman.y = (centerY - (relY * (y*-1) ))- 75

                    }else{
                        pacman.y = (centerY + (relY * y)) - 75
                    }
                    txtCoor.text = "X: " + x + " Y: " + y + " Z: " + z

                    for (i in 0..(arreglo.size-1)){
                        var bA = arreglo.get(i)
                        var x = bA.x
                        var y = bA.y
                        var w = screenWidth / cuantos
                        var h = screenHeight / cuantos
                        var pX = pacman.x
                        var pY = pacman.y
                        val cOx = pacman.x + (pacman.width/2)
                        val cOy = pacman.y + (pacman.height/2)
                        if (bA.visibility == View.VISIBLE){
                            if (cOx>=x && cOx<(x+w) && cOy> y && cOy<(y+h)){
                                Log.e("Entro", "Entro")
                                bA.visibility = View.INVISIBLE
                            }
                        }

                    }//for arreglo

                }//llave tiempo


            }//llave if acelerometro
        }
    }


}