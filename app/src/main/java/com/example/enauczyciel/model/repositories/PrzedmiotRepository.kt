package com.example.enauczyciel.model.repositories

import androidx.lifecycle.LiveData
import com.example.enauczyciel.model.Przedmiot
import com.example.enauczyciel.model.PrzedmiotDao

class PrzedmiotRepository(private val przedmiotDao: PrzedmiotDao) {

    val readAll:LiveData<List<Przedmiot>> = przedmiotDao.wszystkiePrzedmioty()

    suspend fun add(przedmiot: Przedmiot){
        przedmiotDao.insert(przedmiot)
    }

     fun getById(id:Int): LiveData<Przedmiot> {
        return przedmiotDao.znajdzPrzedmiot(id)

    }
}