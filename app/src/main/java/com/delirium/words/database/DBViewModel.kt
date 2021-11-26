package com.delirium.words.database

import androidx.lifecycle.ViewModel
import com.delirium.words.WordRepository
import com.delirium.words.model.Word
import java.util.*

open class DBViewModel : ViewModel() {
    private val wordRepository = WordRepository.get()
    val wordListLiveData = wordRepository.getWords()
    fun wordLiveData(id: UUID) = wordRepository.getWord(id)

    fun update(word: Word) {
        wordRepository.updateWord(word)
    }
}