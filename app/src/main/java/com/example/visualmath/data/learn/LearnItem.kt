package com.example.visualmath.data.learn

import androidx.room.*

@Entity
data class LearnItem (
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "learn_name") val name: String,
        @ColumnInfo(name = "learn_type") val type: LearnType,
        @ColumnInfo(name = "is_complete") val isComplete: Boolean,
        @ColumnInfo(name = "content") val content: List<String>,
        )

enum class LearnType{
    Theory,
    Quiz
}