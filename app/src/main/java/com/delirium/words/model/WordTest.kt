package com.delirium.words.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class WordTest(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val date: String
)