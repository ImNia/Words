package com.delirium.words.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.delirium.words.model.Word
import java.util.*

@Dao
interface WordDao {
    @Insert
    fun insertWord(word: Word)

    @Query("SELECT * FROM Word")
    fun getWords(): LiveData<List<Word>>

    @Query("SELECT * FROM Word WHERE id=(:id)")
    fun getWord(id: UUID): LiveData<Word?>
}