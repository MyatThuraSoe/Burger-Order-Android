package com.example.glorytaste.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://glorytaste-backend.onrender.com"

    // Create and provide Retrofit instance
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Create and provide MenuApiService instance
    val menuApiService: MenuApiService by lazy {
        retrofit.create(MenuApiService::class.java)
    }
}
