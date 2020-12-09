package com.example.enauczyciel.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("Select * from student_table")
    fun wszyscyStudenci():LiveData<List<Student>>

    @Query("Select * from student_table where imie=:imie and nazwisko=:nazwisko limit 1")
    fun znajdzStudenta(imie:String,nazwisko:String):LiveData<Student>

    @Query("Select id from student_table where imie=:imie and nazwisko=:nazwisko limit 1")
    fun znajdzIdStudenta(imie:String,nazwisko:String):Int


}