package com.example.enauczyciel.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*


@Entity(tableName = "oceny_table",
        foreignKeys = [
            ForeignKey(
                    entity=Student::class,
                    parentColumns = ["id"],
                    childColumns = ["studentID"],
                    onDelete = CASCADE),
            ForeignKey(
                    entity = Przedmiot::class,
                    parentColumns = ["id"],
                    childColumns = ["przedmiotID"],
                    onDelete = CASCADE
            )

        ])
data class Ocena(
        @PrimaryKey(autoGenerate = true)val id:Int,
        val studentID: Int,
        val przedmiotID: Int,

        val wartosc:Double,
        val notatka:String)



