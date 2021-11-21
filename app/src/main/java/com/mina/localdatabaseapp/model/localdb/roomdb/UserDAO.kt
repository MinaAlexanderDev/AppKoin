package com.ahmedtawfik.kotlinappnavigation.model.local.roomdb

import androidx.room.*
import com.ahmedtawfik.kotlinappnavigation.model.entity.User

@Dao
interface UserDAO {

    @Query("select * from user_table")
     fun getUsers(): List<User>
//    suspend
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertOrUpdateUser(user: User?)
//    suspend
    @Delete
     fun deleteUser(user: User?)
//    suspend

    @Update
    fun updateUser(user: User?)
}