package com.delirium.words

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.delirium.words.database.WordDatabase
import com.delirium.words.model.Word
import java.util.*

private const val DATABASE_NAME = "usersword.db"

class WordRepository private constructor(context: Context) {
    private val database: WordDatabase = Room.databaseBuilder(
        context.applicationContext,
        WordDatabase::class.java,
        DATABASE_NAME,
    ).allowMainThreadQueries().build()

    private val wordDao = database.wordDao()

    fun insertWord(word: Word) = wordDao.insertWord(word)

    fun getWords() : LiveData<List<Word>> = wordDao.getWords()

    fun getWord(id: UUID): LiveData<Word?> = wordDao.getWord(id)

    companion object {
        private var INSTANCE : WordRepository? = null
        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = WordRepository(context)
            }
        }

        fun get(): WordRepository {
            return INSTANCE ?: throw IllegalStateException("WordRepository not initialized")
        }
    }
}