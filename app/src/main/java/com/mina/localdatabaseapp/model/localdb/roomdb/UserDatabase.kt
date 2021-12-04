package com.mina.localdatabaseapp.model.localdb.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mina.localdatabaseapp.model.entitymodel.User

private const val DATABASE_NAME = "product_dataBase"

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            return instance ?: synchronized(Any()) {
                instance ?: buildDataBase(context).also { instance = it }
            }
        }

        private fun buildDataBase(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}