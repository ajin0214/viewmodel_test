package com.example.myapplication

class BoxOfficeResult(
    val boxofficeType: String?,
    val showRange: String?,
    val dailyBoxOfficeList: List<BoxOfficeDetailResult>? // 수정 필요
)