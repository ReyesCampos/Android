package com.example.s_money.adapters

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s_money.R
import com.example.s_money.models.Ingreso
import java.util.ArrayList

class AdapterIngresos(val ingreso: ArrayList<Ingreso>): RecyclerView.Adapter<AdapterIngresos.ViewHolder>() {
    var position = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterIngresos.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_ingreso, parent, false)
        return AdapterIngresos.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdapterIngresos.ViewHolder, position: Int) {
        this.position = position
        holder.bindItems(ingreso[position])
    }

    override fun getItemCount(): Int {
        return ingreso.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }
        @SuppressLint("ResourceAsColor")
        fun bindItems(ingreso: Ingreso) {
            val txtnombre = itemView.findViewById<TextView>(R.id.name)
            val icono = itemView.findViewById<ImageView>(R.id.icon)
            val txtmonto = itemView.findViewById<TextView>(R.id.mont)
            val barra = itemView.findViewById<ProgressBar>(R.id.pbar)

            if (ingreso.nombre.equals("Sueldo")){
                icono.setImageResource(R.drawable.ic_sueldo)
                icono.backgroundTintList = ColorStateList.valueOf(Color.rgb(179, 223, 182))
                barra.progressTintList = ColorStateList.valueOf(Color.rgb(179, 223, 182))
            }
            if (ingreso.nombre.equals("Depósito")){
                icono.setImageResource(R.drawable.ic_deposito)
                icono.backgroundTintList = ColorStateList.valueOf(Color.rgb(179, 182, 180))
                barra.progressTintList = ColorStateList.valueOf(Color.rgb(179, 182, 180))
            }
            if (ingreso.nombre.equals("Ahorro")){
                icono.setImageResource(R.drawable.ic_ahorro)
                icono.backgroundTintList = ColorStateList.valueOf(Color.rgb(179, 171, 227))
                barra.progressTintList = ColorStateList.valueOf(Color.rgb(179, 171, 227))
            }
            txtnombre.text = ingreso.nombre

            txtmonto.text = ingreso.monto.toString()



        }

    }
}