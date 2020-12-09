package com.example.enauczyciel.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.enauczyciel.model.Przedmiot
import com.example.enauczyciel.model.Student

import com.example.enauczyciel.model.StudiaDatabase
import com.example.enauczyciel.model.repositories.PrzedmiotRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListaZajecViewModel(application: Application) : AndroidViewModel(application) {

    val listaPrzedmiotow: LiveData<List<Przedmiot>>



    private val przedmiotRepository: PrzedmiotRepository

    init {

        listaPrzedmiotow = StudiaDatabase.getDatabase(application).przedmiotDao().wszystkiePrzedmioty()

        przedmiotRepository = PrzedmiotRepository(StudiaDatabase.getDatabase(application).przedmiotDao())

//        GlobalScope.launch{
//            initializeDB()
//        }

    }
//    fun updateStudents(student: Student):{
//        MainViewModel.wybranyPrzedmiot
//    }
    suspend fun initializeDB(){
        przedmiotRepository.add(przedmiot = Przedmiot(0,"Programowanei"))
        przedmiotRepository.add(przedmiot = Przedmiot(0,"Android"))
    }
    suspend fun dodajPrzedmiot(nazwa:String){
        przedmiotRepository.add(Przedmiot(0,nazwa))
    }


}