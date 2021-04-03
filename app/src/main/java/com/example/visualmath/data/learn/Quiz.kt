package com.example.visualmath.data.learn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quiz(
      @PrimaryKey val learnItemId: Int,
      @ColumnInfo(name = "questions") val questions: List<Question>
)