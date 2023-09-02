package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val kobisRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createKobisApiService(): KobisApiService {
        return kobisRetrofit.create(KobisApiService::class.java)
    }

    private val tmdbRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createTmdbApiService(): TmdbApiService {
        return tmdbRetrofit.create(TmdbApiService::class.java)
    }

}
