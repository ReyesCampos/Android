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
import com.example.s_money.models.Gasto
import java.util.*


class  AdapterGastos(val gasto: ArrayList<Gasto>): RecyclerView.Adapter<AdapterGastos.ViewHolder>() {
    var position = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterGastos.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_gasto, parent, false)
        return AdapterGastos.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdapterGastos.ViewHolder, position: Int) {
        this.position = position
        holder.bindItems(gasto[position])
    }

    override fun getItemCount(): Int {
        return gasto.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }
        @SuppressLint("ResourceAsColor")
        fun bindItems(gasto: Gasto) {
            val txtnombre = itemView.findViewById<TextView>(R.id.nombre)
            val icono = itemView.findViewById<ImageView>(R.id.icono)
            val txtmonto = itemView.findViewById<TextView>(R.id.monto)
            val barra = itemView.findViewById<ProgressBar>(R.id.barra)

            if (gasto.nombre.equals("Salud")){
                icono.setImageResource(R.drawable.ic_salud)
                icono.backgroundTintList = ColorStateList.valueOf(Color.rgb(179, 223, 182))
                barra.progressTintList = ColorStateList.valueOf(Color.rgb(179, 223, 182))
            }
            if (gasto.nombre.equals("Transporte")){
                icono.setImageResource(R.drawable.ic_transporte)
                icono.backgroundTintList = ColorStateList.valueOf(Color.rgb(179, 182, 180))
                barra.progressTintList = ColorStateList.valueOf(Color.rgb(179, 182, 180))
            }
            if (gasto.nombre.equals("Facturas")){
                icono.setImageResource(R.drawable.ic_facturas)
                icono.backgroundTintList = ColorStateList.valueOf(Color.rgb(179, 171, 227))
                barra.progressTintList = ColorStateList.valueOf(Color.rgb(179, 171, 227))
            }
            if (gasto.nombre.equals("General")){
                icono.setImageResource(R.drawable.ic_general)
                icono.backgroundTintList = ColorStateList.valueOf(Color.rgb(	179, 255, 113))
                barra.progressTintList = ColorStateList.valueOf(Color.rgb(	179, 255, 113))
            }
            txtnombre.text = gasto.nombre

            txtmonto.text = gasto.monto.toString()



        }

    }
}