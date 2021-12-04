package com.mina.localdatabaseapp.di


import UsersListViewModel
import android.app.Application
import androidx.room.Room
import com.mina.localdatabaseapp.model.Repository
import com.mina.localdatabaseapp.model.RepositoryImp
import com.mina.localdatabaseapp.model.localdb.roomdb.DatabaseRepository
import com.mina.localdatabaseapp.model.localdb.roomdb.DatabaseRepositoryImp
import com.mina.localdatabaseapp.model.localdb.roomdb.UserDatabase
import com.mina.localdatabaseapp.remote.RemoteRepository
import com.mina.localdatabaseapp.remote.RemoteRepositoryImp
import com.mina.localdatabaseapp.remote.ServiceAPI

import org.koin.android.ext.koin.androidApplication
 import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
 import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val DATABASE_NAME = "user_database"
const val BaseURL: String = "https://my-json-server.typicode.com/"

val viewModelModule = module {
    viewModel { UsersListViewModel(repository = get())}
}


val repositoryModule = module {
    single<RemoteRepository> { RemoteRepositoryImp(api = get()) }
    single<DatabaseRepository> { DatabaseRepositoryImp(db = get()) }
    single<Repository> { RepositoryImp(remoteRepository = get(), databaseRepository = get()) }
}

val serviceAPIModule = module {

    fun getRetroBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { getRetroBuilder() }

    fun getServiceAPIInstance(retrofit: Retrofit): ServiceAPI {
        return retrofit.create(ServiceAPI::class.java)
    }

    single { getServiceAPIInstance(retrofit = get()) }

}

val dataBaseModule = module {
    fun getDatabaseInstance(application: Application): UserDatabase {
        return Room
            .databaseBuilder(
                application,
                UserDatabase::class.java,
                DATABASE_NAME
            )
            .build()
    }

    single { getDatabaseInstance(androidApplication()) }
}

//val viewModelModule = module {
//    viewModel { UsersListViewModel(repository = get()) }
//}
//
//
//val repositoryModule = module {
//     single<RemoteRepository> { RemoteRepositoryImp(api = get()) }
//     single<DatabaseRepository> { DatabaseRepositoryImp(db = get()) }
//    single { RepositoryImp(remoteRepository = get(), databaseRepository = get()) }
////    singleBy<  RepositoryImp(remoteRepository = get(), databaseRepository = get())>()
////singleBy<RepositoryImp(remote)>()
//}
//
//val serviceAPIModule = module {
//    fun getRetroBuilder(): Retrofit {
//        return Retrofit.Builder().baseUrl(BaseURL)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//
//    }
//    single { getRetroBuilder() }
//
//
//    fun getServiceAPIInstance(retrofit: Retrofit):ServiceAPI {
//        return retrofit.create(ServiceAPI::class.java)
//    }
//
//    single { getServiceAPIInstance(retrofit = get()) }
//}
//
//val dataBaseModule  = module {
//    fun getDatabaseDataBase(application: Application): UserDatabase {
//        return Room.databaseBuilder(
//            application,
//            UserDatabase::class.java,
//            DATABASE_NAME
//        ).build()
//    }
//    single { getDatabaseDataBase(androidApplication()) }
//}
