package com.android.inventarioapp.view_sales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.inventarioapp.R
import com.android.inventarioapp.SQLManager

class ViewSalesActivity : AppCompatActivity() {
    lateinit var base: SQLManager
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_sales)
        base = SQLManager(this)
        button = findViewById(R.id.button2)

        button.setOnClickListener {
            val lista = base.getSalidasCabeceras(this)
            val lista2 = base.getSalidasDetalles(this)

            for ( a in lista){
                Log.i("cab", a.SalCabNum.toString())
            }

            for ( b in lista2){
                Log.i("det", b.CanCam.toString())
            }
        }
    }
}