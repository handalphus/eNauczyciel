package com.example.enauczyciel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(@PrimaryKey(autoGenerate = true) val id: Int,  val imie: String, val nazwisko:String) {

}