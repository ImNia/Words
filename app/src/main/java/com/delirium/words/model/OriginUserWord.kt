package com.delirium.words.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_words")
data class OriginUserWord(
    @PrimaryKey
    val id: UUID,
    val origin: String,
    var progress: Double
)