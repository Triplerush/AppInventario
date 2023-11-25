package com.android.inventarioapp.view_sales

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.inventarioapp.R
import com.android.inventarioapp.class_tables.SalidaCabecera

class rvViewSales(private var list: ArrayList<SalidaCabecera>) : RecyclerView.Adapter<ShirtViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShirtViewHolder {
        val context = parent.context
        return ShirtViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_recicler_view_codsale, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ShirtViewHolder, position: Int) {
        holder.render(list[position])
    }

    fun changeResult(listFilter: ArrayList<SalidaCabecera>){
        this.list = listFilter
        notifyDataSetChanged()
    }
}

class ShirtViewHolder(private val context: Context, view: View) : RecyclerView.ViewHolder(view) {
    val txtCode = view.findViewById<TextView>(R.id.txtCode)
    val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
    val txtNameClient = view.findViewById<TextView>(R.id.txtNameClient)
    val txtDate = view.findViewById<TextView>(R.id.txtDate)

    fun render(sale: SalidaCabecera) {
        txtCode.text = sale.SalCabNum.toString()
        txtPrice.text = sale.CabPre.toString()
        txtNameClient.text = sale.SalCabCli
        "${sale.SalCabDay}/${sale.SalCabMon}/${sale.SalCabYear}".also { txtDate.text = it }
    }

}
