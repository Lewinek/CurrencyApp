package com.example.core_networking

interface CurrencyRepository {
    suspend fun getRatesByBaseCurrency(): ResultWrapper<List<Rate>>
}

class CurrencyRepositoryImpl(private val api: CurrencyApi) : CurrencyRepository {
    override suspend fun getRatesByBaseCurrency(): ResultWrapper<List<Rate>> {
        return safeApiCall { api.getRatesByBaseCurrency("GBP").convertedRates }
    }
}