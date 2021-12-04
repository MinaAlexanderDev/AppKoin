package com.mina.localdatabaseapp.model.localdb.roomdb

import com.mina.localdatabaseapp.model.entitymodel.User

interface DatabaseRepository {
    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getUsers():List<User>
    suspend fun updateUser(user: User)
    suspend fun searchUser(userId: Int):List<User>
}