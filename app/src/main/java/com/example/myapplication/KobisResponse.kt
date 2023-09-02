package com.example.myapplication

data class DailyBoxOfficeListResult(
    val boxOfficeResult: BoxOfficeResult?
) {
    fun getDailyBoxOfficeList(): List<DailyBoxOfficeResult>? {
        return boxOfficeResult?.dailyBoxOfficeList
    }
}

data class BoxOfficeResult(
    val boxofficeType: String?,
    val showRange: String?,
    val dailyBoxOfficeList: List<DailyBoxOfficeResult>?
)

data class DailyBoxOfficeResult(
    val rnum: String?,
    val rank: String?,
    val movieCd: String?,
    val movieNm: String,
    val openDt: String,
    val salesAmt: String?,
    val salesShare: String?,
    val salesInten: String?,
    val salesChange: String?,
    val salesAcc: String?,
    val audiCnt: String?,
    val audiInten: String?,
    val audiChange: String?,
    val audiAcc: String?,
    val scrnCnt: String?,
    val showCnt: String?,
    var posterPath: String? = null
)