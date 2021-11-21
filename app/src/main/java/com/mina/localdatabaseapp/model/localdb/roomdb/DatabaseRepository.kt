package com.mina.localdatabaseapp.model.localdb.roomdb

import com.ahmedtawfik.kotlinappnavigation.model.entity.User

interface DatabaseRepository {
    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getUsers():List<User>
    suspend fun updateUser(user: User)
}