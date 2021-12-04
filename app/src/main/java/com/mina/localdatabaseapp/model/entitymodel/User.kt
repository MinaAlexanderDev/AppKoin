package com.mina.localdatabaseapp.model.entitymodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "message")
    var message: String,

    @ColumnInfo(name = "imageId")
    var imageId:Int

    )