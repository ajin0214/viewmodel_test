package com.example.myapplication

class KobisRepository {
    suspend fun getDailyBoxOfficeList(targetDate: String): DailyBoxOfficeListResult {
        return KobisRetrofitInstance.createKobisApiService().getDailyBoxOfficeList(
            apiKey = "9bd03980caf9c2e3af6dd832245794cf",
            targetDate = targetDate
        )
    }
}
