package com.mina.localdatabaseapp.remote

import com.mina.localdatabaseapp.model.entitymodel.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepositoryImp(private val api: ServiceAPI) : RemoteRepository {
    override suspend fun getAPIUsers() =
        withContext(Dispatchers.IO) {
            api.getAPIUsers()

        }

    override suspend fun getAPIUser(id: Int): Response<User> =
        withContext(Dispatchers.IO) {
            api.getAPIUser(id)

        }

    override suspend fun getAPIUserQuery(id: Int): Response<User> =
    withContext(Dispatchers.IO) {
        api.getAPIUserQuery(id)


     }

    override suspend fun addAPIUser(user: User): Response<User>  =
        withContext(Dispatchers.IO) {
            api.addAPIUser(user)
        }

    override suspend fun updateAPIUser(user: User, id: Int): Response<User>   =
        withContext(Dispatchers.IO) {
            api.updateAPIUser(user,id)
        }

    override suspend fun deleteAPIUser(id: Int): Response<User>   =
        withContext(Dispatchers.IO) {
            api.deleteAPIUser(id)
        }
}