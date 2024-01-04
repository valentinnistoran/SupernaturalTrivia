package com.example.spntrivia.gameHistoryDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val difficulty: Int?,
    val score: Long?,
    val rank: Int?,
    val rankTitle: String?
)
