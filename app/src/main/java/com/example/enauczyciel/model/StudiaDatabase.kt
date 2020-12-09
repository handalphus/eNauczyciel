package com.example.enauczyciel.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase





@Database(
    entities = [Student::class, Przedmiot::class, Ocena::class, StudentPrzedmiot::class],
    version = 4,
    exportSchema = false
)
abstract class StudiaDatabase :RoomDatabase(){

    abstract fun studentDao():StudentDao
    abstract fun przedmiotDao():PrzedmiotDao
    abstract fun ocenaDao(): OcenaDao
    abstract fun studentPrzedmiotDao():StudentPrzedmiotDao

    companion object{

        @Volatile
        private var INSTANCE: StudiaDatabase?=null

        fun getDatabase(context: Context):StudiaDatabase{
            val tempInstance = INSTANCE

            if(tempInstance!=null)
                return tempInstance
            else
                synchronized(this)
                {
                    val instance= Room.databaseBuilder(
                        context.applicationContext,
                        StudiaDatabase::class.java,
                        "my_database"
                    ).build()
                    INSTANCE=instance
                    return instance

                }
        }
    }
}