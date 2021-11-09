package com.delirium.words.database

import androidx.lifecycle.ViewModel
import com.delirium.words.WordRepository
import com.delirium.words.model.Word

open class DBViewModel : ViewModel() {
    val wordRepository = WordRepository.get()
    val wordListLiveData = wordRepository.getWords()

    fun update(word: Word) {
        wordRepository.updateWord(word)
    }
}