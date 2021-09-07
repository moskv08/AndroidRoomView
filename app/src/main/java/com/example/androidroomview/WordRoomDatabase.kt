package com.example.androidroomview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidroomview.entities.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
public abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object{

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        // Create a singelton database object or just return it
        fun getDatabase(context: Context): WordRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_dataase"
                ).build()
                INSTANCE = instance
                // Return instance
                instance
            }
        }
    }
}