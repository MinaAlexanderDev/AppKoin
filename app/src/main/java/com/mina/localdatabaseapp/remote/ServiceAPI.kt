package com.mina.localdatabaseapp.remote

import com.mina.localdatabaseapp.model.entitymodel.User
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {

    @GET("/MinaAlexanderDev/FakeRestfulAPI/users")
    suspend fun getAPIUsers(): Response<List<User>>

    @GET("/MinaAlexanderDev/FakeRestfulAPI/users/{id}")
    suspend fun getAPIUser(@Path("id" )id: Int): Response<User>

    @GET("/MinaAlexanderDev/FakeRestfulAPI/users/")
    suspend fun getAPIUserQuery(@Query("id" )id: Int): Response<User>

    @POST("/MinaAlexanderDev/FakeRestfulAPI/users")
    suspend fun addAPIUser(@Body user: User): Response<User>

    @PUT("/MinaAlexanderDev/FakeRestfulAPI/users/{id}")
    suspend fun updateAPIUser(@Body user: User,@Path("id" )id: Int): Response<User>

    @DELETE("/MinaAlexanderDev/FakeRestfulAPI/users/{id}")
    suspend fun deleteAPIUser(@Path("id" )id: Int): Response<User>
}