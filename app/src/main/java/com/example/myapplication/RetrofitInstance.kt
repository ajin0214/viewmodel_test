package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createMovieApiService(): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}
