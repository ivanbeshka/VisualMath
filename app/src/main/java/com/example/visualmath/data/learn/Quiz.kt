package com.example.visualmath.data.learn

import androidx.room.*

@Entity
data class Quiz(
        @PrimaryKey override val id: Int,
        @ColumnInfo(name = "name") override val name: String,
//        @ColumnInfo(name = "questions") val questions: List<Question>,
        @ColumnInfo(name = "is_complete") override val isComplete: Boolean = false,
) : LearnItem

@Dao
interface QuizDao {
    @Insert
    fun insertAll(vararg quiz: Quiz)

    @Update
    fun update(vararg quiz: Quiz)

    @Delete
    fun delete(vararg quiz: Quiz)

    @Query("SELECT * FROM quiz")
    fun getAll(): List<Quiz>

    @Query("SELECT * FROM quiz WHERE id = :id")
    fun getQuiz(id: Int): Quiz
}

@Entity
data class Question(
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "answers") val answers: List<String>,
    @ColumnInfo(name = "right_answers") val rightAnswers: List<String>
)