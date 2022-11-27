package com.example.core_networking

interface CurrencyRepository {
    suspend fun getRatesByBaseCurrency(name: String): ResultWrapper<List<Rate>>
}

class CurrencyRepositoryImpl(private val api: CurrencyApi) : CurrencyRepository {
    override suspend fun getRatesByBaseCurrency(name: String): ResultWrapper<List<Rate>> {
        return safeApiCall { api.getRatesByBaseCurrency(name).convertedRates }
    }
}