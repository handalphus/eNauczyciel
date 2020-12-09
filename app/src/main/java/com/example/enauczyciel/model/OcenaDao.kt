package com.example.enauczyciel.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface OcenaDao {
    @Insert
    suspend fun insert(ocena: Ocena)

    @Delete
    suspend fun delete(ocena: Ocena)

    @Query("select * from oceny_table")
    fun wszystkiePrzedmioty(): LiveData<List<Ocena>>

    @Query("select * from oceny_table where studentID=:idStudenta and przedmiotID = :idPrzedmiotu ")
    fun znajdzOcenyStudenta(idStudenta:Int,idPrzedmiotu:Int):LiveData<List<Ocena>>
}