package com.example.enauczyciel.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enauczyciel.R
import com.example.enauczyciel.viewmodel.*
import kotlinx.android.synthetic.main.fragment_wpisywanie_ocen.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [wpisywanieOcen.newInstance] factory method to
 * create an instance of this fragment.
 */
class wpisywanieOcen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter1: ListaOcenAdapter
    lateinit var viewManager1: RecyclerView.LayoutManager
    lateinit var ocenianieViewModel: OcenianieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        ocenianieViewModel = ViewModelProvider(requireActivity()).get(OcenianieViewModel::class.java)
        viewManager1 = LinearLayoutManager(requireContext())

        adapter1 = ListaOcenAdapter(ocenianieViewModel.listaOcen)
        ocenianieViewModel.listaOcen.observe(viewLifecycleOwner,{adapter1.notifyDataSetChanged()})



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wpisywanie_ocen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter1.notifyDataSetChanged()
        textViewImie.text = MainViewModel.wybranyStudent.imie
        textViewNazwisko.text = MainViewModel.wybranyStudent.nazwisko
        buttonDodajOcene.setOnClickListener {
            val ocena: Double = editTextNumberDecimalOcena.text?.toString()?.toDouble()?:2.0
            GlobalScope.launch{ ocenianieViewModel.ocenStudenta(ocena,
                    editTextTextNotatka.text.toString()
            )}

        }
        button2.setOnClickListener {
            view->
            view.findNavController().navigate(R.id.action_wpisywanieOcen_to_listaStudentow)
        }
        recyclerViewOceny.apply{
            adapter=adapter1
            layoutManager=viewManager1
        }
    }

    override fun onResume() {
        super.onResume()
        ocenianieViewModel.updateGrades()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment wpisywanieOcen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                wpisywanieOcen().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}