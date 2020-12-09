package com.example.enauczyciel.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "student_przedmiot_table",
foreignKeys =[
    ForeignKey(entity = Student::class,
    parentColumns = ["id"],
    childColumns = ["student_id"],
    onDelete = CASCADE
    ),
    ForeignKey(entity = Przedmiot::class,
        parentColumns = ["id"],
        childColumns = ["przedmiot_id"],
        onDelete = CASCADE
    )
    ]

    )
data class StudentPrzedmiot(
    @PrimaryKey(autoGenerate = true)val id:Int,
    val student_id: Int,
    val przedmiot_id: Int,
    )