package com.example.core_networking

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest")
    fun getRatesByBaseCurrency(@Query("base") base: String): CurrencyResponse
}