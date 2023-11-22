package com.android.inventarioapp.view_shirt

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.inventarioapp.class_tables.Shirt
import com.android.inventarioapp.R


class rvShirtsAdapter(private val list:Array<Shirt>):RecyclerView.Adapter<ShirtViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShirtViewHolder {
        return ShirtViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recicler_view_shirt,parent,false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ShirtViewHolder, position: Int) {
        holder.render(list[position])
    }

}

class ShirtViewHolder(view: View):RecyclerView.ViewHolder(view){
    val txtName = view.findViewById<TextView>(R.id.txtShirt)
    val txtAmount = view.findViewById<TextView>(R.id.txtAmount)
    val imageShirt = view.findViewById<ImageView>(R.id.imageShirt)

    fun render(shirt: Shirt){
        txtName.text = shirt.CamNom
        txtAmount.text = "Cantidad: ${shirt.CamCan}"
        imageShirt.setImageURI(Uri.parse(shirt.CamIma))
    }

}