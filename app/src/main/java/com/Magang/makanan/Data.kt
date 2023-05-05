package com.Magang.makanan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Data(private val Nama : ArrayList<String>,private val Gambar : ArrayList<String>, private val context : Context) : RecyclerView.Adapter<Data.DataList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataList {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_list, parent,false)
        return DataList(view)
    }

    override fun onBindViewHolder(holder: DataList, position: Int) {
        holder.NamaList.text = Nama.get(position)
        Picasso.get().load(Gambar.get(position)).into(holder.IconLabel);
        holder.IconLabel.setOnClickListener {
            val intent = Intent()
            intent.setClass(holder.view.context,Deskripsi::class.java)
            intent.putExtra("nama",position)
            holder.view.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return Nama.size
    }

    class DataList(view : View) : RecyclerView.ViewHolder(view){
        val NamaList = view.findViewById<TextView>(R.id.Title_Label)
        val IconLabel = view.findViewById<ImageView>(R.id.Icon_Brand)
        val view = view
    }
}