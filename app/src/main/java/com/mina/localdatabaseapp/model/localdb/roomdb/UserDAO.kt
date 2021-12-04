package com.mina.localdatabaseapp.model.localdb.roomdb

import androidx.room.*
import com.mina.localdatabaseapp.model.entitymodel.User

@Dao
interface UserDAO {

    @Query("select * from user_table")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateUser(user: User?)

    @Delete
    fun deleteUser(user: User?)

    @Update
    fun updateUser(user: User?)

    @Query("SELECT * FROM user_table WHERE id =:userId")
    fun searchUser(userId: Int): List<User>



}