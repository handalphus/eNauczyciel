package com.example.enauczyciel.viewmodel
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.enauczyciel.R
import com.example.enauczyciel.model.Przedmiot
import com.example.enauczyciel.view.MainActivity
import java.io.Console


class ListaPrzedmiotowAdapter(var data: LiveData<List<Przedmiot>>):RecyclerView.Adapter<ListaPrzedmiotowAdapter.Holder>() {

    lateinit var context: Context

    class Holder(view: View):RecyclerView.ViewHolder(view)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=LayoutInflater.from(parent.context).
        inflate(R.layout.list_row,parent,false) as View

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textView1=holder.itemView.findViewById<TextView>(R.id.textViewNazwaPrzedmiotu)
        textView1.text=data.value?.get(position)?.nazwa?:""
        val buttonZobaczPrzedmiot=holder.itemView.findViewById<Button>(R.id.buttonZobaczPrzedmiot)

        buttonZobaczPrzedmiot.setOnClickListener {
             view ->
                 run{

                    MainViewModel.wybranyPrzedmiot= data.value!![position]
                     Log.v("wybranyPrzedmiot","${MainViewModel.wybranyPrzedmiot}")
                     view.findNavController()
                        .navigate(R.id.action_listaZajec_to_listaStudentow)
                }

        }
    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }

}