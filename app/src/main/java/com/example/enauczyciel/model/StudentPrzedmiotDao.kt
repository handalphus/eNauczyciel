package com.example.enauczyciel.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface StudentPrzedmiotDao {
    @Insert
     fun insert(studentPrzedmiot: StudentPrzedmiot)

    @Delete
    suspend fun delete(studentPrzedmiot: StudentPrzedmiot)

   @Query("Select * from student_table where id in (Select student_id from student_przedmiot_table where przedmiot_id=:przedmiot)")
   fun znajdzStudentowPrzedmiotu(przedmiot:Int):LiveData<List<Student>>

}