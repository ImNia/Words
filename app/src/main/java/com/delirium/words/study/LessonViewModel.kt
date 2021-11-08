package com.delirium.words.study

import androidx.lifecycle.ViewModel
import com.delirium.words.WordRepository
import com.delirium.words.model.Word

class LessonViewModel : ViewModel() {
    private val wordRepository = WordRepository.get()
    val wordListLiveData = wordRepository.getWords()

    fun update(word: Word) {
        wordRepository.updateWord(word)
    }
}