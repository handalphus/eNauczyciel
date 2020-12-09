package com.example.enauczyciel.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.enauczyciel.R
import com.example.enauczyciel.model.Ocena

class ListaOcenAdapter(var data: LiveData<List<Ocena>>): RecyclerView.Adapter<ListaOcenAdapter.Holder>() {

    lateinit var context: Context

    class Holder(view: View): RecyclerView.ViewHolder(view)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.ocena_row,parent,false) as View

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textView1=holder.itemView.findViewById<TextView>(R.id.textViewWartosc)
        textView1.text=data.value?.get(position)?.wartosc.toString()
        val textView2=holder.itemView.findViewById<TextView>(R.id.textViewNotatka)
        textView2.text=data.value?.get(position)?.notatka

    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }

}