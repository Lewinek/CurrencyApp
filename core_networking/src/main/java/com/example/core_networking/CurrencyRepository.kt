package com.example.core_networking

interface CurrencyRepository {
    suspend fun getRatesByBaseCurrency(name: String): ResultWrapper<List<Currency>>
}

class CurrencyRepositoryImpl(private val api: CurrencyApi) : CurrencyRepository {
    override suspend fun getRatesByBaseCurrency(name: String): ResultWrapper<List<Currency>> {
        return safeApiCall { api.getRatesByBaseCurrency(name).convertedRates.map { it.toCurrency() } }
    }
}