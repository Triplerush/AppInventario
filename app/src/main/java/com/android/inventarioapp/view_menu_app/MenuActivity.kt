package com.android.inventarioapp.view_menu_app

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.android.inventarioapp.MainActivity2
import com.android.inventarioapp.R
import com.android.inventarioapp.view_add_sales.AddSalesActivity
import com.android.inventarioapp.view_add_shirt.AddShirtActivity
import com.android.inventarioapp.view_delete_shirt.DeleteShirtActivity
import com.android.inventarioapp.view_sales.ViewSalesActivity
import com.android.inventarioapp.view_search_shirts.SearchShirtActivity
import com.android.inventarioapp.view_extra_data.AddExtraDataActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var btnAddShirt: Button
    private lateinit var btnViewShirt: Button
    private lateinit var btnRemoveShirt: Button
    private lateinit var btnViewSale: Button
    private lateinit var btnAddSale: Button
    private lateinit var btnAddExtraData: Button
    private lateinit var btnExit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnAddShirt = findViewById(R.id.btnAddShirt)
        btnViewShirt = findViewById(R.id.btnViewShirt)
        btnRemoveShirt = findViewById(R.id.btnDeleteShirt)
        btnViewSale = findViewById(R.id.btnViewSales)
        btnAddSale = findViewById(R.id.btnAddSales)
        btnAddExtraData = findViewById(R.id.btnAddExtraData)
        btnExit = findViewById(R.id.btnExit)
    }

    private fun initListeners() {
        btnAddShirt.setOnClickListener { navigateToAddShirtActivity() }
        btnViewShirt.setOnClickListener { navigateToViewShirtActivity() }
        btnRemoveShirt.setOnClickListener { navigateToDeleteActivity() }
        btnAddSale.setOnClickListener { navigateToAddSalesActivity() }
        btnViewSale.setOnClickListener { navigateToViewSalesActivity() }
        btnAddExtraData.setOnClickListener { navigateToAddExtraData() }
        btnExit.setOnClickListener { navigateToExit() }

    }

    private fun navigateToAddShirtActivity() {
        val intent = Intent(this, AddShirtActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToViewShirtActivity() {
        val intent = Intent(this, SearchShirtActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToDeleteActivity() {
        val intent = Intent(this, DeleteShirtActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAddSalesActivity() {
        val intent = Intent(this, AddSalesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToViewSalesActivity() {
        val intent = Intent(this, ViewSalesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAddExtraData() {
        val intent = Intent(this, AddExtraDataActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToExit() {
        finish()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val result = isGranted
            } else {

            }
            Log.i("permiso",isGranted.toString())
        }

    private fun requestPermissionHandler() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Permiso ya concedido
            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                // El usuario ha denegado el permiso previamente, puedes mostrar un mensaje explicativo aquí
            }
            else -> {
                requestPermissionLauncher.launch(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }
    }
}