//package com.mina.localdatabaseapp.ui.userlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mina.localdatabaseapp.model.Repository
import com.mina.localdatabaseapp.model.entitymodel.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersListViewModel(private val repository: Repository) : ViewModel() {

    private var usersListMutableLiveData = MutableLiveData<List<User>>()

    val usersListLiveData: LiveData<List<User>> get() = usersListMutableLiveData

    private var usersAPIMutableLiveData = MutableLiveData<List<User>>()

    val usersAPILiveData: LiveData<List<User>> get() = usersAPIMutableLiveData

    private var addUserAPIMutableLiveData = MutableLiveData<User>()

    val addUserAPILiveData: LiveData<User> get() = addUserAPIMutableLiveData

    fun getUsersList() = viewModelScope.launch {
        usersListMutableLiveData.postValue(repository.getUsers())
    }

    fun getUsersAPI() = viewModelScope.launch {
        var result = repository.getAPIUsers()

        if (result.isSuccessful) {
            if (result.body() != null) {
                usersAPIMutableLiveData.postValue(result.body())
            }
        } else {
            Log.i("errMsg", result.message())
        }
    }

    fun addUserAPI(user: User) = viewModelScope.launch {
        var result = repository.addAPIUser(user)

        if (result.isSuccessful) {
            if (result.body() != null) {
                addUserAPIMutableLiveData.postValue(result.body())
            }
        } else {
            Log.i("errMsg", result.message())
        }
    }

    fun deleteAPIUser(id: Int) {
        viewModelScope.launch {
            repository.deleteAPIUser(id)
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

}
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//
//import com.mina.localdatabaseapp.model.Repository
//import com.mina.localdatabaseapp.model.entitymodel.User
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class UsersListViewModel(private val repository: Repository): ViewModel() {
//    val tag: String = "UsersListViewModel"
//
////    //remote data variables
////    private var remoteRepositoryImp: RemoteRepositoryImp
////    //local data variables
////    private var localRepositoryImp: DatabaseRepositoryImp
//
//    //list of data from API
//    private var usersAPIMutableLiveData = MutableLiveData<List<User>>()
//    val usersAPILiveData: LiveData<List<User>> get() = usersAPIMutableLiveData
//
//    //remote add user to API
//    private var addUsersAPIMutableLiveData = MutableLiveData<User>()
//    val addUserAPILiveData: LiveData<User> get() = addUsersAPIMutableLiveData
//
//    //list of data from local db
//    private var usersMutableLiveData = MutableLiveData<List<User>>()
//    val usersLiveData: LiveData<List<User>> get() = usersMutableLiveData
//
//
////    init {
////
////        val db = UserDatabase.getInstance(application)
////        Log.e("getAllUsers", "db :$db ")
////
////        //get data from API
////        val serviceInstance = RetroBuilder.getRetroBuilder().create(ServiceAPI::class.java)
////        remoteRepositoryImp = RemoteRepositoryImp(serviceInstance)
////        Log.e("getAllUsers", "localRepositoryImp :$remoteRepositoryImp ")
////
////        //get data from local db
////        localRepositoryImp = DatabaseRepositoryImp(db)
////        Log.e("getAllUsers", "localRepositoryImp :$localRepositoryImp ")
////
////    }
//
//    //get users from local db
//    private fun getUsers() = viewModelScope.launch {
//        Log.e("getAllUsers", "getAllUsers")
//        usersMutableLiveData.postValue(repository.getUsers())
//
//    }
//
//    //add users to local db
//    fun addUser(user: User) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addUser(user)
//            getUsers()
//        }
//
//    }
//    //delete users from local db
//
//    fun deleteUser(user: User) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteUser(user)
//            getUsers()
//        }
//    }
//
//    //update users from local db
//    fun updateUser(user: User) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateUser(user)
//            getUsers()
//        }
//    }
//
//    //search users from local db
//    fun searchUser(userId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            usersMutableLiveData.postValue(repository.searchUser(userId))
//
//        }
//    }
//
//
//    //get users from remote API
//    fun getUsersAPI() = viewModelScope.launch {
//        Log.e(tag, "getUsersAPI")
//        var result = repository.getAPIUsers()
//        if (result.isSuccessful) {
//            if (result.body() != null) {
//                usersAPIMutableLiveData.postValue(result.body())
//
//            } else {
//                Log.i(tag, "Error ${result.message()}")
//            }
//        }
//
////        usersMutableLiveData.postValue(localRepositoryImp.getUsers())
//
//    }
//
//    //get users from remote API
//    fun addUserAPI(user: User) = viewModelScope.launch {
//        Log.e(tag, "getUsersAPI")
//        var result = repository.addAPIUser(user)
//        if (result.isSuccessful) {
//            if (result.body() != null) {
//                addUsersAPIMutableLiveData.postValue(result.body())
//
//            } else {
//                Log.i(tag, "Error ${result.message()}")
//            }
//        }
//
////        usersMutableLiveData.postValue(localRepositoryImp.getUsers())
//
//    }
//
//    fun deleteUserAPI(id: Int) = viewModelScope.launch {
//        Log.e(tag, "getUsersAPI")
//        var result = repository.deleteAPIUser(id)
//        if (result.isSuccessful) {
//            if (result.body() != null) {
//                Log.i(tag, "Error ${result.body()}")
//
////                addUsersAPIMutableLiveData.postValue(result.body())
//
//            } else {
//                Log.i(tag, "Error ${result.message()}")
//            }
//        }
//
////        usersMutableLiveData.postValue(localRepositoryImp.getUsers())
//
//    }
//
//}