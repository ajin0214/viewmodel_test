package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KobisRetrofitInstance {
    private val kobisRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createKobisApiService(): KobisApiService {
        return kobisRetrofit.create(KobisApiService::class.java)
    }
}
