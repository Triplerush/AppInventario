package com.android.inventarioapp.view_sale_read

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.inventarioapp.R
import com.android.inventarioapp.SQLManager
import com.android.inventarioapp.class_tables.SalidaCabecera
import com.android.inventarioapp.class_tables.SalidaDetalle

class ReadSaleActivity : AppCompatActivity() {
    private lateinit var rvShirts: RecyclerView
    private lateinit var txtInputCode: TextView
    private lateinit var txtInputClient: TextView
    private lateinit var txtInputPrice: TextView
    private lateinit var txtInputDate: TextView
    private lateinit var salCab: SalidaCabecera
    private lateinit var btnCancelar: Button
    private lateinit var btnDelete: Button


    private lateinit var code: String
    private lateinit var client: String
    lateinit var base: SQLManager
    private lateinit var adapterShirtAction: rvReadSaleAdapter
    private lateinit var lista: ArrayList<SalidaDetalle>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_sale)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        base = SQLManager(this)
        code = intent.extras?.getString("EXTRA_TEXT").orEmpty()
        salCab = base.getOneSalidaCabecera(this, code.toInt())!!
        client = base.getOneCliente(this,salCab.SalCabCli)!!.CliNom

        txtInputCode = findViewById(R.id.txtInputCode)
        txtInputClient = findViewById(R.id.txtInputClient)
        txtInputPrice = findViewById(R.id.txtInputPrice)
        txtInputDate = findViewById(R.id.txtInputDate)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnDelete = findViewById(R.id.btnDelete)

        txtInputCode.text = salCab.SalCabNum.toString()
        txtInputClient.text = client
        "$/. ${salCab.CabPre}".also { txtInputPrice.text = it }
        "${salCab.SalCabDay }/${salCab.SalCabMon }/${salCab.SalCabYear }".also { txtInputDate.text = it }

        rvShirts = findViewById(R.id.rvShirts)
        lista = ArrayList(base.getSalidasDetalles(this,code).asList())
        adapterShirtAction = rvReadSaleAdapter(
            lista,
        )
        rvShirts.apply {
            layoutManager = LinearLayoutManager(this@ReadSaleActivity)
            adapter = adapterShirtAction
        }
    }

    private fun initListeners() {
        btnCancelar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnDelete.setOnClickListener {
            deleteSale()
        }
    }

    private fun deleteSale() {
        base.deleteData(this, code,"SalCabNum","Salidas_cab")

        for( a in lista){
            base.deleteData(this,a.SalDetCod.toString(),"SalDetCod","Salidas_det")
        }
    }
}