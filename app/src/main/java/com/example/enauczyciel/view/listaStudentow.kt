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
import com.example.enauczyciel.viewmodel.ListaStudentowAdapter
import com.example.enauczyciel.viewmodel.ListaStudentowViewModel
import com.example.enauczyciel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_lista_studentow.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [listaStudentow.newInstance] factory method to
 * create an instance of this fragment.
 */
class listaStudentow : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter1: ListaStudentowAdapter
    lateinit var viewManager1: RecyclerView.LayoutManager
    lateinit var listaStudentowViewModel: ListaStudentowViewModel
    lateinit var mainViewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // FragmentTransaction tr = getFragmentManager()
        listaStudentowViewModel = ViewModelProvider(requireActivity()).get(ListaStudentowViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewManager1 = LinearLayoutManager(requireContext())

        adapter1 = ListaStudentowAdapter(listaStudentowViewModel.listaStudentow)

        listaStudentowViewModel.listaStudentow.observe(viewLifecycleOwner,{adapter1.notifyDataSetChanged()})


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_studentow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter1.notifyDataSetChanged()
        layout2.setOnClickListener{
            view->view.findNavController().navigate(R.id.action_listaStudentow_to_dodawanieStudentowZListy)
        }
        buttonWstecz.setOnClickListener { view->

            view.findNavController().navigate(R.id.action_listaStudentow_to_listaZajec)

         }
        recyclerViewStudenci.apply{
            adapter=adapter1
            layoutManager=viewManager1


        }
        buttonDodajStudenta.setOnClickListener {
            val imie = editTextTextImieStudenta.text.toString()
            val nazwisko = editTextTextNazwiskoStudenta.text.toString()
            if (imie != "" && nazwisko !="") {

                editTextTextImieStudenta.setText("")
                editTextTextNazwiskoStudenta.setText("")
                GlobalScope.launch {
                    listaStudentowViewModel.dodajStudentPrzedmiot(imie,nazwisko)

                }
//                listaStudentowViewModel.updateStudents()
            }

        }

    }

    override fun onResume() {
        super.onResume()
        listaStudentowViewModel.updateStudents()
    }

    override fun onPause() {
        super.onPause()

        listaStudentowViewModel.updateStudents()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ocenianie.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            listaStudentow().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}