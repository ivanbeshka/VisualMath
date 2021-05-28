package com.example.visualmath.data.learn

import androidx.room.*

@Entity
data class Theory(
        @PrimaryKey
        override val id: Int,

        @ColumnInfo(name = "name")
        override val name: String,

        @ColumnInfo(name = "is_complete")
        override val isComplete: Boolean = false,
) : LearnItem

@Dao
interface TheoryDao {
    @Insert
    fun insertAll(vararg theory: Theory)

    @Update
    fun update(vararg theory: Theory)

    @Delete
    fun delete(vararg theory: Theory)

    @Query("SELECT * FROM theory")
    fun getAll(): List<Theory>

    @Query("SELECT * FROM theory WHERE id = :id")
    fun getQuiz(id: Int): Theory
}