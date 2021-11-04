package com.delirium.words.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "word")
data class Word(
    @PrimaryKey
    val id: UUID,
    val origin: String,
    val translate: String,
    val progress: Double
)