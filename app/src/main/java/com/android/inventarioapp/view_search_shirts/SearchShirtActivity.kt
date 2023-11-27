package com.android.inventarioapp.view_search_shirts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.inventarioapp.R
import com.android.inventarioapp.SQLManager
import com.android.inventarioapp.class_tables.Shirt
import com.android.inventarioapp.databinding.ActivitySearchShirtBinding
import com.android.inventarioapp.view_add_shirt.AddShirtActivity
import com.google.android.material.textfield.TextInputEditText
import rvSearchAdapter

class SearchShirtActivity : AppCompatActivity() {
    private lateinit var rvSearch: RecyclerView
    private lateinit var btnVolver: Button
    private lateinit var btnAdd: Button
    private lateinit var lista: ArrayList<Shirt>
    private lateinit var txtInputSearch: TextInputEditText

    private lateinit var binding: ActivitySearchShirtBinding


    lateinit var adapterShirtAction : rvSearchAdapter
    lateinit var base: SQLManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_shirt)
        initComponents()
        initListeners()
    }

    private fun initComponents(){
        base = SQLManager(this)

        binding = ActivitySearchShirtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtInputSearch = findViewById(R.id.txtInputSearch)
        txtInputSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filtrar(s.toString())
            }

        })

        rvSearch = findViewById(R.id.rvShirtsActions)
        btnAdd = findViewById(R.id.btnInicio)
        lista = ArrayList(base.getCamisetas(this).asList().filter { camiseta -> camiseta.EstCam == 1 })
        adapterShirtAction = rvSearchAdapter(lista)
        btnVolver = findViewById(R.id.btnVolver)

        rvSearch.apply {
            layoutManager = LinearLayoutManager(this@SearchShirtActivity)
            adapter = adapterShirtAction
        }

    }
    private fun initListeners() {
        btnVolver.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnAdd.setOnClickListener { navigateToAddShirtActivity() }
    }

    private fun navigateToAddShirtActivity() {
        val intent = Intent(this, AddShirtActivity::class.java)
        startActivity(intent)
    }

    private fun filtrar(texto: String){
        val listaFiltrada = ArrayList<Shirt>()

        lista.forEach {
            if(it.CamNom.lowercase().contains(texto.lowercase())){
                listaFiltrada.add(it)
            }
        }

        adapterShirtAction.changeResult(listaFiltrada)
    }
}
