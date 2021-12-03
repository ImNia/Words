package com.delirium.words.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "meaning_word")
data class MeaningWord(
    @PrimaryKey
    val id: UUID,
    val origin: String,
    val translate: String
)