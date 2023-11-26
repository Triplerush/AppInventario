package com.android.inventarioapp.view_add_data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.inventarioapp.MainActivity2
import com.android.inventarioapp.R

class AddDataActivity : AppCompatActivity() {
    lateinit var btnVolver: Button
    lateinit var btnViewBrand: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnVolver = findViewById(R.id.btnVolver)
        btnViewBrand = findViewById(R.id.btnViewBrand)

    }

    private fun initListeners() {
        btnVolver.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnViewBrand.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}