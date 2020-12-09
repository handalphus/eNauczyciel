package com.example.enauczyciel.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.enauczyciel.model.Przedmiot
import com.example.enauczyciel.model.Student
import com.example.enauczyciel.model.StudiaDatabase
import com.example.enauczyciel.model.repositories.PrzedmiotRepository
import com.example.enauczyciel.model.repositories.StudentRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.Console
import kotlin.properties.Delegates


class ListaDostepnychStudentowViewModel(application: Application):AndroidViewModel(application) {
    private val studentRepository: StudentRepository
//            StudentRepository(
//                    studentDao = StudiaDatabase.getDatabase(application).studentDao(),
//                    studentPrzedmiotDao = StudiaDatabase.getDatabase(application).studentPrzedmiotDao()
//            )

    var listaStudentow: LiveData<List<Student>>
//        get()  {
//            Log.v("wybrany przedmiot",MainViewModel.wybranyPrzedmiot.toString())
//            Log.v("id przedmiotu", MainViewModel.wybranyPrzedmiot.id.toString())
//
//            return studentRepository.getByPrzedmiot(MainViewModel.wybranyPrzedmiot.id)
////            return MainViewModel.wybraniStudenci
//        }
    init {

        studentRepository =   StudentRepository(
                studentDao = StudiaDatabase.getDatabase(application).studentDao(),
                studentPrzedmiotDao = StudiaDatabase.getDatabase(application).studentPrzedmiotDao()
        )
//       listaStudentow = studentRepository.getAccessibleStudents(MainViewModel.wybranyPrzedmiot.id)
    listaStudentow = studentRepository.readAll
    }
    val app = application
    fun updateStudents(){
        Log.v("Wybrany przedmiot", MainViewModel.wybranyPrzedmiot.toString())
        listaStudentow = studentRepository.getByPrzedmiot(MainViewModel.wybranyPrzedmiot.id)
    }

    fun wpiszStudentow(){
//        viewModelScope.launch {
//        listaStudentow = StudiaDatabase.getDatabase(app).studentPrzedmiotDao()
//            .znajdzStudentowPrzedmiotu(MainViewModel.wybranyPrzedmiot.id)
//    }
    }
//    suspend fun initializeStudent(){
//        studentRepository.add(Student(0,"Jan", "Kowalski"))
//        studentRepository.add(Student(0,"Janusz", "Miera"))
//    }

    suspend fun dodajStudenta(student: Student) {
        studentRepository.dopiszStudentaDoPrzedmiotu(student, MainViewModel.wybranyPrzedmiot.id)

    }
    suspend fun dodajStudentPrzedmiot(imie: String,nazwisko: String){
        studentRepository.dopiszStudentaDoPrzedmiotu(imie,nazwisko,MainViewModel.wybranyPrzedmiot.id)
    }

}