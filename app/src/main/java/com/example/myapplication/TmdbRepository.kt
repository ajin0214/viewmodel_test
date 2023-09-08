package com.example.myapplication

class TmdbRepository {
    suspend fun searchMovie(query: String, year: String): TmdbResponse {
        return TmdbRetrofitInstance.createTmdbApiService().searchMovie(
            apiKey = "cad81b4f21905c64ded0cd7048cf7fd4",
            query = query,
            year = year,
            language = "ko"
        )
    }
}
