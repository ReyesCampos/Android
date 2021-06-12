package com.example.s_money.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s_money.R
import com.example.s_money.adapters.AdapterIngresos
import com.example.s_money.conexion.Conexion
import com.example.s_money.models.Ingreso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentIngresos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentIngresos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_ingresos, container, false)

        var conexion = Conexion(view.context)
        var db = conexion.writableDatabase

        db.execSQL("insert into ingresos (nombre, monto) values ('Sueldo', '4000')")
        db.execSQL("insert into ingresos (nombre, monto) values ('Depósito', '700')")
        db.execSQL("insert into ingresos (nombre, monto) values ('Ahorro', '500')")
        //select count(*) from gastos
        //select SUM(monto) from gastos
        //db.execSQL("select SUM(monto) from gastos")
        //db.execSQL("select count(*) from gastos")
        //var cant = db.rawQuery("select count(*) from gastos", null)
        //lateinit var total:Cursor
        //total = db.rawQuery("select sum(monto) as Total from gastos", null)

        var recyclerView= view.findViewById<RecyclerView>(R.id.recycleViewIngresos)
        recyclerView.layoutManager= LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        var ingresos=ArrayList<Ingreso>()
        var respuesta = db.rawQuery("select * from ingresos", null)

        if(respuesta.moveToFirst()){

            do {
                ingresos.add(Ingreso(respuesta.getString(1), respuesta.getDouble(2)))
                Log.e("DATO", respuesta.getString(1))
                //Log.e("Cantidad", cant.toString())
                //Log.e("Total", total.toString())

                //Toast.makeText(context, "Cantidad" + cant.getDouble(0), Toast.LENGTH_LONG).show()
                //Toast.makeText(context, "Total" + total.getDouble(0), Toast.LENGTH_LONG).show()
            }while (respuesta.moveToNext())
        }else{
            Log.e("DATO", "SIN DATOS")
        }

        var adapterIn = AdapterIngresos(ingresos)
        recyclerView.adapter = adapterIn

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentIngresos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentIngresos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}