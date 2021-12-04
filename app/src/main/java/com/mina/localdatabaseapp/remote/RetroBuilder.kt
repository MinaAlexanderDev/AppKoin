package com.mina.localdatabaseapp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroBuilder {
    companion object {
        const val BaseURL: String = "https://my-json-server.typicode.com/"
        fun getRetroBuilder(): Retrofit {
            return Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build()

        }
    }
}