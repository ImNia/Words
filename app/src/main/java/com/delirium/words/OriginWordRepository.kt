package com.delirium.words

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.delirium.words.database.OriginWordDatabase
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import com.delirium.words.model.OriginWord
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "KnowledgeBase.db"

class WordRepository private constructor(context: Context) {
    private val database: OriginWordDatabase = Room.databaseBuilder(
        context.applicationContext,
        OriginWordDatabase::class.java,
        DATABASE_NAME,
    ).build()

    private val executor = Executors.newSingleThreadExecutor()
    private val originWordDao = database.wordDao()

    fun insertWord(originWord: OriginWord) {
        executor.execute {
            originWordDao.insertWord(originWord)
        }
    }
    fun insertUserWord(originUserWord: OriginUserWord) {
        executor.execute {
            originWordDao.insertUserWord(originUserWord)
        }
    }
    fun insertMeaningWord(meaningWord: MeaningWord) {
        executor.execute {
            originWordDao.insertMeaningWord(meaningWord)
        }
    }

    fun updateWord(originUserWord: OriginUserWord) {
        executor.execute {
            originWordDao.updateWord(originUserWord)
        }
    }

    fun getWords() : LiveData<List<OriginWord>> = originWordDao.getWords()

    fun getWord(id: UUID): LiveData<OriginWord?> = originWordDao.getWord(id)

    fun getUserWords() : LiveData<List<OriginUserWord>> = originWordDao.getUserWords()

    fun getUserWord(id: UUID): LiveData<OriginUserWord?> = originWordDao.getUserWord(id)

    fun getTranslate(origin: String): LiveData<List<MeaningWord>> = originWordDao.getTranslate(origin)

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