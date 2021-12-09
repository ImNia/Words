package com.delirium.words.database

import androidx.lifecycle.ViewModel
import com.delirium.words.WordRepository
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import java.util.*

open class DBViewModel : ViewModel() {
    private val wordRepository = WordRepository.get()

    fun insertUserWord(word: OriginUserWord) = wordRepository.insertUserWord(word)
    fun insertMeaningWord(word: MeaningWord) = wordRepository.insertMeaningWord(word)

    val wordListLiveData = wordRepository.getWords()
    fun wordLiveData(id: UUID) = wordRepository.getWord(id)

    val userWordListLiveData = wordRepository.getUserWords()
    fun userWordLiveData(id: UUID) = wordRepository.getUserWord(id)

    fun update(originUserWord: OriginUserWord) {
        wordRepository.updateWord(originUserWord)
    }

    fun wordTranslation(origin: String) = wordRepository.getTranslate(origin)
}