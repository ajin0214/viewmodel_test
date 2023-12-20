package com.example.myapplication.data.movie.source.remote.kobis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KobisRetrofitInstance {
    fun createKobisApiService(): KobisApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KobisApiService::class.java)
    }
}
