package com.example.androidroomview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidroomview.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Database(entities = [Word::class], version = 1, exportSchema = false)
public abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()

                    // Delete all content here.
                    wordDao.deleteAll()

                    // Add sample words.
                    var word = Word(null,"Hello")
                    wordDao.insert(word)
                    word = Word(null, "World!")
                    wordDao.insert(word)

                    // TODO: Add your own words!
                    word = Word(null,"TODO!")
                    wordDao.insert(word)
                }
            }
        }
    }


    companion object{

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        // Create a singleton database object or just return it
        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // Return instance
                instance
            }
        }
    }
}