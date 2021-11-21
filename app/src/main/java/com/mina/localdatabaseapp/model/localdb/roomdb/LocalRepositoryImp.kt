package com.ahmedtawfik.kotlinappnavigation.model.local.roomdb

import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import com.mina.localdatabaseapp.model.localdb.roomdb.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImp(private val db: UserDatabase) : DatabaseRepository {
    override suspend fun addUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDao().insertOrUpdateUser(user)
        }
    }

    override suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDao().deleteUser(user)
        }
    }

    override suspend fun getUsers() = withContext(Dispatchers.IO) {
        db.userDao().getUsers()
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDao().updateUser(user)
        }


     }

}