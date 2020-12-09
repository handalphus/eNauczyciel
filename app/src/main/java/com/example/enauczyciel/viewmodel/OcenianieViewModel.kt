package com.example.enauczyciel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enauczyciel.model.Ocena
import com.example.enauczyciel.model.Przedmiot
import com.example.enauczyciel.model.StudiaDatabase
import com.example.enauczyciel.model.repositories.OcenyRepository


class OcenianieViewModel(application: Application) : AndroidViewModel(application) {
//    private var ocenyArrayList= MainViewModel.biezaceOceny
//
//    private var ocenyList = MutableLiveData(ocenyArrayList)
//
//
//
    private val ocenyRepository:OcenyRepository
    var listaOcen: LiveData<List<Ocena>>
   init {
       ocenyRepository = OcenyRepository(StudiaDatabase.getDatabase(application).ocenaDao())
       listaOcen = ocenyRepository.znajdzOcenyZPrzemiotu(MainViewModel.wybranyStudent.id,MainViewModel.wybranyPrzedmiot.id)
   }
    fun updateGrades(){
        listaOcen = ocenyRepository.znajdzOcenyZPrzemiotu(MainViewModel.wybranyStudent.id,MainViewModel.wybranyPrzedmiot.id)

    }
//
//
    suspend fun ocenStudenta(ocena:Double, notatka:String) {
        ocenyRepository.dopiszOcene(MainViewModel.wybranyStudent.id,MainViewModel.wybranyPrzedmiot.id,3.0,"Kolokwium")
    }
}