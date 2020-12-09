package com.example.enauczyciel.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.enauczyciel.R
import com.example.enauczyciel.viewmodel.ListaPrzedmiotowAdapter
import com.example.enauczyciel.viewmodel.ListaZajecViewModel
import kotlinx.android.synthetic.main.fragment_lista_zajec.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [listaZajec.newInstance] factory method to
 * create an instance of this fragment.
 */
class listaZajec : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter1: ListaPrzedmiotowAdapter
    lateinit var viewManager1: RecyclerView.LayoutManager
    lateinit var listaZajecViewModel: ListaZajecViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        listaZajecViewModel = ViewModelProvider(requireActivity()).get(ListaZajecViewModel::class.java)
        viewManager1 = LinearLayoutManager(requireContext())

        adapter1 = ListaPrzedmiotowAdapter(listaZajecViewModel.listaPrzedmiotow)
        listaZajecViewModel.listaPrzedmiotow.observe(viewLifecycleOwner,{adapter1.notifyDataSetChanged()})


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_zajec, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply{
            adapter=adapter1
            layoutManager=viewManager1


        }
        buttonDodajZajecia.setOnClickListener {
            val nazwa = editTextTextNazwaPrzedmiotu.text.toString()
            if (nazwa != "") {
                editTextTextNazwaPrzedmiotu.setText("")
                GlobalScope.launch {
                    listaZajecViewModel.dodajPrzedmiot(nazwa)

                }
            }

        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment listaZajec.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                listaZajec().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}