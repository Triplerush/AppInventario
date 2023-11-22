package com.android.inventarioapp.view_shirt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.inventarioapp.R
import com.android.inventarioapp.SQLManager
import com.android.inventarioapp.view_add_shirt.AddShirtActivity
import com.android.inventarioapp.view_search_shirts.SearchShirtActivity

class ViewShirtActivity : AppCompatActivity() {
    private lateinit var rvShirts: RecyclerView
    private lateinit var btnVolver: Button
    private lateinit var btnAdd: Button
    private lateinit var btnBuscar: Button
    private lateinit var btnEliminar: Button


    lateinit var adapterShirt : rvShirtsAdapter
    lateinit var base: SQLManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_shirt)
        initComponents()
        initListeners()
    }

    private fun initComponents(){
        base = SQLManager(this)
        rvShirts = findViewById(R.id.rvShirts)

        btnAdd = findViewById(R.id.btnAdd)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnEliminar = findViewById(R.id.btnEliminar)

        val lista = base.getCamisetas(this)
        adapterShirt = rvShirtsAdapter(
            lista
        )
        btnVolver = findViewById(R.id.btnVolver)
        rvShirts.apply {
            layoutManager = LinearLayoutManager(this@ViewShirtActivity)
            adapter = adapterShirt
        }

    }
    private fun initListeners() {
        btnVolver.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnBuscar.setOnClickListener {
            navigateToSearchShirtsActivity()
        }

        btnAdd.setOnClickListener { navigateToAddShirtActivity() }

    }

    private fun navigateToAddShirtActivity() {
        val intent = Intent(this, AddShirtActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSearchShirtsActivity() {
        val intent = Intent(this, SearchShirtActivity::class.java)
        startActivity(intent)
    }
}