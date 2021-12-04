package com.mina.localdatabaseapp

import android.app.Application
import com.mina.localdatabaseapp.di.dataBaseModule
import com.mina.localdatabaseapp.di.repositoryModule
import com.mina.localdatabaseapp.di.serviceAPIModule
import com.mina.localdatabaseapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApp:Application() {
    override fun onCreate() {
        super.onCreate()

    startKoin {
        androidContext(this@UsersApp)
    modules(
        listOf(
        viewModelModule,
        repositoryModule,
        serviceAPIModule,
        dataBaseModule
    ))

    }

    }
}