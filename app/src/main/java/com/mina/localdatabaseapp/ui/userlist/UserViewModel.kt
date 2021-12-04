package com.mina.localdatabaseapp.ui.userlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mina.localdatabaseapp.model.entitymodel.User
import com.mina.localdatabaseapp.model.localdb.roomdb.LocalRepositoryImp
import com.mina.localdatabaseapp.model.localdb.roomdb.UserDatabase
import com.mina.localdatabaseapp.remote.RemoteRepositoryImp
import com.mina.localdatabaseapp.remote.RetroBuilder
import com.mina.localdatabaseapp.remote.ServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val tag: String = "UserViewModel"

    //remote data variables
    private var remoteRepositoryImp: RemoteRepositoryImp
    private var usersAPIMutableLiveData = MutableLiveData<List<User>>()
    val usersAPILiveData: LiveData<List<User>> get() = usersAPIMutableLiveData

    //remote add user to API
    private var addUsersAPIMutableLiveData = MutableLiveData<User>()
    val addUserAPILiveData: LiveData<User> get() = addUsersAPIMutableLiveData

    //local data variables
    private var localRepositoryImp: LocalRepositoryImp
    private var usersMutableLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = usersMutableLiveData


    init {

        val db = UserDatabase.getInstance(application)
        Log.e("getAllUsers", "db :$db ")

        //get data from API
        val serviceInstance = RetroBuilder.getRetroBuilder().create(ServiceAPI::class.java)
        remoteRepositoryImp = RemoteRepositoryImp(serviceInstance)
        Log.e("getAllUsers", "localRepositoryImp :$remoteRepositoryImp ")

        //get data from local db
        localRepositoryImp = LocalRepositoryImp(db)
        Log.e("getAllUsers", "localRepositoryImp :$localRepositoryImp ")

    }

    //get users from local db
    private fun getUsers() = viewModelScope.launch {
        Log.e("getAllUsers", "getAllUsers")
        usersMutableLiveData.postValue(localRepositoryImp.getUsers())

    }

    //add users to local db
    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepositoryImp.addUser(user)
            getUsers()
        }

    }
    //delete users from local db

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepositoryImp.deleteUser(user)
            getUsers()
        }
    }

    //update users from local db
    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepositoryImp.updateUser(user)
            getUsers()
        }
    }

    //search users from local db
    fun searchUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            usersMutableLiveData.postValue(localRepositoryImp.searchUser(userId))

        }
    }


    //get users from remote API
    fun getUsersAPI() = viewModelScope.launch {
        Log.e(tag, "getUsersAPI")
        var result = remoteRepositoryImp.getAPIUsers()
        if (result.isSuccessful) {
            if (result.body() != null) {
                usersAPIMutableLiveData.postValue(result.body())

            } else {
                Log.i(tag, "Error ${result.message()}")
            }
        }

//        usersMutableLiveData.postValue(localRepositoryImp.getUsers())

    }

    //get users from remote API
    fun addUserAPI(user: User) = viewModelScope.launch {
        Log.e(tag, "getUsersAPI")
        var result = remoteRepositoryImp.addAPIUser(user)
        if (result.isSuccessful) {
            if (result.body() != null) {
                addUsersAPIMutableLiveData.postValue(result.body())

            } else {
                Log.i(tag, "Error ${result.message()}")
            }
        }

//        usersMutableLiveData.postValue(localRepositoryImp.getUsers())

    }

    fun deleteUserAPI(id: Int) = viewModelScope.launch {
        Log.e(tag, "getUsersAPI")
        var result = remoteRepositoryImp.deleteAPIUser(id)
        if (result.isSuccessful) {
            if (result.body() != null) {
                Log.i(tag, "Error ${result.body()}")

//                addUsersAPIMutableLiveData.postValue(result.body())

            } else {
                Log.i(tag, "Error ${result.message()}")
            }
        }

//        usersMutableLiveData.postValue(localRepositoryImp.getUsers())

    }

}