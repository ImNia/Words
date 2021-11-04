package com.delirium.words.study

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.delirium.words.WordRepository
import com.delirium.words.model.Word
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class Lesson : ViewModel() {

    private val wordRepository = WordRepository.get()
    val wordListLiveData = wordRepository.getWords()

    /*lateinit var wordListData: LiveData<Word?>
    private lateinit var tmpListData: LiveData<List<Word>>
    val latch = CountDownLatch(1)

    fun getWord(uuid: UUID) : Word? {

      *//*  database.insertWord(Word(
            id = UUID.fromString("3acc325f-55cb-4711-8b35-4094589ce86b"),
            origin = "ensure",
            translate = "ensureTest",
            progress = 0.0
        ))
*//*

        wordListData = database.getWord(uuid)
        latch.await(2, TimeUnit.SECONDS);
        tmpListData = database.getWords()

        Log.i("LESSONSPAGE", "Data from DB: ${wordListData.value}")

        val firstWord: Word? = wordListData.value

        firstWord?.let {
            Log.i("LESSONSPAGE", it.origin)
        } ?: Log.e("LESSONSPAGE", "Don't get data from database")

        return firstWord
    }*/
}