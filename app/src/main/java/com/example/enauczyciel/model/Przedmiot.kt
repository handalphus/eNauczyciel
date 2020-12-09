package com.example.enauczyciel.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "przedmioty_table")
data class Przedmiot(@PrimaryKey(autoGenerate = true) val id:Int,val nazwa: String)


