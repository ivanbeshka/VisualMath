package com.example.visualmath.data.learn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Theory(
        @PrimaryKey val learnItemId: Int,
        @ColumnInfo(name = "content") val content: List<String>,
        //todo bitmap
        )
