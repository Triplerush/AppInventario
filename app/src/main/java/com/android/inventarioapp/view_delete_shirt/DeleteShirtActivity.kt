package com.android.inventarioapp.view_delete_shirt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.inventarioapp.R
import com.android.inventarioapp.SQLManager
import com.android.inventarioapp.class_tables.Shirt
import com.android.inventarioapp.view_add_shirt.AddShirtActivity
import com.android.inventarioapp.view_menu_app.MenuActivity
import com.google.android.material.textfield.TextInputEditText

class DeleteShirtActivity : AppCompatActivity() {
    private lateinit var rvSearch: RecyclerView
    private lateinit var btnVolver: Button
    private lateinit var btnDelete: Button
    private lateinit var lista: ArrayList<Shirt>
    private lateinit var txtInputSearch: TextInputEditText

    lateinit var adapterShirtAction: rvDeleteAdapter
    lateinit var base: SQLManager

    val listaDelete = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_shirt)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        base = SQLManager(this)

        txtInputSearch = findViewById(R.id.txtInputSearch)
        txtInputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filtrar(s.toString())
            }
        })

        rvSearch = findViewById(R.id.rvShirtsActions)
        btnDelete = findViewById(R.id.btnDelete)
        lista = ArrayList(base.getCamisetas(this).asList())
        adapterShirtAction = rvDeleteAdapter(
            lista,
            { flag, cod -> selectedDelete(flag, cod) }
        )
        btnVolver = findViewById(R.id.btnVolver)

        rvSearch.apply {
            layoutManager = LinearLayoutManager(this@DeleteShirtActivity)
            adapter = adapterShirtAction
        }

    }

    private fun initListeners() {
        btnVolver.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnDelete.setOnClickListener { deleteShirts() }
    }

    private fun selectedDelete(flag : Boolean, cod: String) {
        if(flag){
            listaDelete.add(cod)
            return
        }
        listaDelete.remove(cod)
    }

    private fun deleteShirts() {
        listaDelete.forEach { elemento ->
            base.deleteShirt(this,elemento)
        }
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finishAffinity()
        Toast.makeText(this@DeleteShirtActivity,"CAMISETAS ELIMINADAS CORRECTAMENTE", Toast.LENGTH_LONG).show()
    }

    private fun filtrar(texto: String) {
        val listaFiltrada = ArrayList<Shirt>()
        lista.forEach {
            if (it.CamNom.lowercase().contains(texto.lowercase()))
                listaFiltrada.add(it)
        }
        adapterShirtAction.changeResult(listaFiltrada)
    }
}
