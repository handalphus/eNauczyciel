package com.example.enauczyciel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.enauczyciel.model.Przedmiot
import com.example.enauczyciel.model.Student
import com.example.enauczyciel.model.StudiaDatabase
import com.example.enauczyciel.model.repositories.PrzedmiotRepository
import com.example.enauczyciel.model.repositories.StudentRepository

class MainViewModel(application: Application) : AndroidViewModel(application){

    companion object{
        private lateinit var _wybranyPrzedmiot: Przedmiot
        var wybranyPrzedmiot:Przedmiot
            get() = _wybranyPrzedmiot
            set(value)  {
                _wybranyPrzedmiot=value

            }


        lateinit var wybranyStudent: Student
    }
}