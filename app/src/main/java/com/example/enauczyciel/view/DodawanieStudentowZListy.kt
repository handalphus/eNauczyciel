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
import kotlinx.android.synthetic.main.fragment_dodawanie_studentow_z_listy.*
import kotlinx.android.synthetic.main.fragment_lista_studentow.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DodawanieStudentowZListy.newInstance] factory method to
 * create an instance of this fragment.
 */
class DodawanieStudentowZListy : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter1: ListaDostepnychStudentowAdapter
    lateinit var viewManager1: RecyclerView.LayoutManager
    lateinit var listaDostepnychStudentowViewModel:ListaDostepnychStudentowViewModel
    lateinit var mainViewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        listaDostepnychStudentowViewModel = ViewModelProvider(requireActivity()).get(ListaDostepnychStudentowViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewManager1 = LinearLayoutManager(requireContext())

        adapter1 = ListaDostepnychStudentowAdapter(listaDostepnychStudentowViewModel.listaStudentow,listaDostepnychStudentowViewModel)

        listaDostepnychStudentowViewModel.listaStudentow.observe(viewLifecycleOwner,{adapter1.notifyDataSetChanged()})



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dodawanie_studentow_z_listy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter1.notifyDataSetChanged()
        buttonWsteczListaDostepnychStudentow.setOnClickListener { view->

            view.findNavController().navigate(R.id.action_dodawanieStudentowZListy_to_listaStudentow)

        }
        recyclerViewDostepniStudenci.apply{
            adapter=adapter1
            layoutManager=viewManager1


        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DodawanieStudentowZListy.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DodawanieStudentowZListy().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}