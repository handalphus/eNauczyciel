package com.example.enauczyciel.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PrzedmiotDao {
    @Insert
    suspend fun insert(przedmiot: Przedmiot)

    @Delete
    suspend fun delete(przedmiot: Przedmiot)

    @Query("select * from przedmioty_table")
    fun wszystkiePrzedmioty():LiveData<List<Przedmiot>>

    @Query("select * from przedmioty_table where id=:id")
    fun znajdzPrzedmiot(id:Int):LiveData<Przedmiot>
}