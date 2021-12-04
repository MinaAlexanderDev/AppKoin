package com.mina.localdatabaseapp.ui.userlist

import UsersListViewModel
import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mina.localdatabaseapp.di.dataBaseModule
import com.mina.localdatabaseapp.di.repositoryModule
import com.mina.localdatabaseapp.di.serviceAPIModule
import com.mina.localdatabaseapp.di.viewModelModule
import com.mina.localdatabaseapp.model.Repository
import com.mina.localdatabaseapp.model.entitymodel.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

import org.koin.test.KoinTest

import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UsersListViewModelTest : KoinTest {
    private val repository :Repository by inject()
    private val viewModelUsers: UsersListViewModel by inject()
    private lateinit var viewModelUsers2: UsersListViewModel

    @Mock private lateinit var context: Application

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
     @Mock
    lateinit var observerData: Observer<List<User>>

    @Before
    fun before() {

        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    serviceAPIModule,
                    dataBaseModule
                ))
//            androidContext(this@UsersApp)
//            modules(
//                viewModelModule,repositoryModule,serviceAPIModule, dataBaseModule )
        }

        viewModelUsers2 = UsersListViewModel(repository)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    @Throws(Exception::class)
       fun getLookUpLeagueList() {
        System.out.println("Test  ")

        val myScope = GlobalScope
        runBlocking {
            System.out.println("Test2")
            myScope.launch(Dispatchers.IO) {
                System.out.println("Test3 ")
              var value=  viewModelUsers.usersAPILiveData.observeForever(observerData)
                Log.d( "error","${value}")
                System.out.println("Test ${value}")
                assert(viewModelUsers.usersAPILiveData.value != null)
                assert(false)
                assert(observerData != null)

            }

        }

    }

}