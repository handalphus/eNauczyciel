package com.example.enauczyciel.viewmodel


import com.example.enauczyciel.model.Student
import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.enauczyciel.R


class ListaStudentowAdapter(var data: LiveData<List<Student>>): RecyclerView.Adapter<ListaStudentowAdapter.Holder>()  {

    lateinit var context: Context

    class Holder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=LayoutInflater.from(parent.context).
        inflate(R.layout.student_list,parent,false) as View

        return Holder(view)
    }

    override fun onBindViewHolder(holder: ListaStudentowAdapter.Holder, position: Int) {
        val textView1=holder.itemView.findViewById<TextView>(R.id.textViewNazwiskoStudenta)
        textView1.text=data.value?.get(position)?.nazwisko?:""
        val textView2=holder.itemView.findViewById<TextView>(R.id.textViewImieStudenta)
        textView2.text=data.value?.get(position)?.imie?:""

        val buttonZobaczOceny=holder.itemView.findViewById<Button>(R.id.buttonOceny)

        buttonZobaczOceny.setOnClickListener {
            view ->     run {
            MainViewModel.wybranyStudent = data.value!![position]

            view.findNavController()
                .navigate(R.id.action_listaStudentow_to_wpisywanieOcen)
        }


        }
    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}