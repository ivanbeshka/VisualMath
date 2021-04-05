package com.example.visualmath.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.visualmath.data.learn.*

@Database(entities = [Quiz::class, Theory::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun quizDao(): QuizDao
    abstract fun theoryDao(): TheoryDao
}