package com.mina.localdatabaseapp.model

import com.mina.localdatabaseapp.model.entitymodel.User
import com.mina.localdatabaseapp.model.localdb.roomdb.DatabaseRepository
import com.mina.localdatabaseapp.remote.RemoteRepository
import retrofit2.Response

class RepositoryImp(private val remoteRepository: RemoteRepository,private val databaseRepository: DatabaseRepository):Repository {
    override suspend fun getAPIUsers()=remoteRepository.getAPIUsers()

    override suspend fun getAPIUser(id: Int)=remoteRepository.getAPIUser(id)

    override suspend fun getAPIUserQuery(id: Int)=remoteRepository.getAPIUserQuery(id)

    override suspend fun addAPIUser(user: User)=remoteRepository.addAPIUser(user)

    override suspend fun updateAPIUser(user: User, id: Int)=remoteRepository.updateAPIUser(user, id)

    override suspend fun deleteAPIUser(id: Int)=remoteRepository.deleteAPIUser(id)

    override suspend fun addUser(user: User)=databaseRepository.addUser(user)

    override suspend fun deleteUser(user: User) =databaseRepository.updateUser(user)

    override suspend fun getUsers()=databaseRepository.getUsers()

    override suspend fun updateUser(user: User) =databaseRepository.updateUser(user)

    override suspend fun searchUser(userId: Int)=databaseRepository.searchUser(userId)
}