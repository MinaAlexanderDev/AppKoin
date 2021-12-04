package com.mina.localdatabaseapp.model.localdb.roomdb

import com.mina.localdatabaseapp.model.entitymodel.User
import com.mina.localdatabaseapp.model.localdb.roomdb.DatabaseRepository
import com.mina.localdatabaseapp.model.localdb.roomdb.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepositoryImp(private val db: UserDatabase) : DatabaseRepository {
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
    override suspend fun searchUser(userId: Int) = withContext(Dispatchers.IO) {
        db.userDao().searchUser(userId)
    }



}