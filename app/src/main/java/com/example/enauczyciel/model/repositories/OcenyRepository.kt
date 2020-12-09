package com.example.enauczyciel.model.repositories

import androidx.lifecycle.LiveData
import com.example.enauczyciel.model.Ocena
import com.example.enauczyciel.model.OcenaDao

class OcenyRepository(private val ocenaDao: OcenaDao) {

    fun znajdzOcenyZPrzemiotu(idStudenta:Int,idPrzedmiotu:Int):LiveData<List<Ocena>>{
        return  ocenaDao.znajdzOcenyStudenta(idStudenta,idPrzedmiotu)
    }

    suspend fun dopiszOcene(idStudenta:Int,idPrzedmiotu:Int,wartosc:Double,notatka:String){
        ocenaDao.insert(Ocena(0,idStudenta,idPrzedmiotu,wartosc, notatka))
    }
}