package com.delirium.words.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import com.delirium.words.model.OriginWord
import java.util.*

@Dao
interface OriginWordDao {
    @Insert
    fun insertWord(originWord: OriginWord)

    @Update
    fun updateWord(originUserWord: OriginUserWord)

    @Query("SELECT * FROM origin_word")
    fun getWords(): LiveData<List<OriginWord>>

    @Query("SELECT * FROM origin_word WHERE id=(:id)")
    fun getWord(id: UUID): LiveData<OriginWord?>

    @Query("SELECT * FROM meaning_word WHERE origin=(:origin)")
    fun getTranslate(origin: String): LiveData<List<MeaningWord>>

    @Query("SELECT * FROM user_words")
    fun getUserWords(): LiveData<List<OriginUserWord>>

    @Query("SELECT * FROM user_words WHERE id=(:id)")
    fun getUserWord(id: UUID): LiveData<OriginUserWord?>
}