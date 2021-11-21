package com.ahmedtawfik.kotlinappnavigation.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "user_name")
    var name: String,

    @ColumnInfo(name = "user_message")
    var message: String,

//    var imageId: Int
)