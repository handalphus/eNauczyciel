package com.example.enauczyciel.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.enauczyciel.model.Student
import com.example.enauczyciel.model.StudentDao
import com.example.enauczyciel.model.StudentPrzedmiot
import com.example.enauczyciel.model.StudentPrzedmiotDao
import kotlinx.coroutines.*

class StudentRepository(private val studentDao: StudentDao,private val studentPrzedmiotDao: StudentPrzedmiotDao) {

    val readAll: LiveData<List<Student>> = studentDao.wszyscyStudenci()

    suspend fun add(imie:String, nazwisko:String){
        studentDao.insert(Student(0,imie,nazwisko))
        Log.v("add","Dodano studenta")
    }
    
   fun getByPrzedmiot(przedmiot: Int):LiveData<List<Student>>{
        return studentPrzedmiotDao.znajdzStudentowPrzedmiotu(przedmiot)
    }

  fun getAccessibleStudents(przedmiot: Int):LiveData<List<Student>>{
      var dostepniStudenci = MutableLiveData<List<Student>>()
      val studenciPrzedmiotu = studentPrzedmiotDao.znajdzStudentowPrzedmiotu(przedmiot).value
      val wszyscyStudenci:List<Student>
      runBlocking {
          wszyscyStudenci =
              GlobalScope.async { studentDao.wszyscyStudenci().value ?: listOf<Student>() }.await()
      }
      Log.v("dostepni studenfi",wszyscyStudenci.toString())
      var listaDostepnych = wszyscyStudenci as MutableList<Student>
      for (i in studenciPrzedmiotu?: listOf()){
           listaDostepnych.removeAt(i.id-1)
      }
      dostepniStudenci.value = listaDostepnych
      return dostepniStudenci
  }
    fun dopiszStudentaDoPrzedmiotu(student: Student, przedmiotId: Int){
        studentPrzedmiotDao.insert(StudentPrzedmiot(0, student.id, przedmiotId))
    }
  fun dopiszStudentaDoPrzedmiotu(imie:String, nazwisko:String, przedmiotId: Int){

      runBlocking { add(imie, nazwisko) }

      val studentId = studentDao.znajdzIdStudenta(imie, nazwisko)

      Log.v("student|przedmiot", "$studentId|$przedmiotId")
      studentPrzedmiotDao.insert(StudentPrzedmiot(0, studentId, przedmiotId))

        }



    }
