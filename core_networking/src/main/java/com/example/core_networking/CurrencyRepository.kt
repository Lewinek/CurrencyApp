package com.example.core_networking

interface CurrencyRepository {
    suspend fun getRatesByBaseCurrency(): List<Rate>
}

class CurrencyRepositoryImpl(private val api: CurrencyApi) : CurrencyRepository {
    override suspend fun getRatesByBaseCurrency(): List<Rate> {
        return api.getRatesByBaseCurrency("GBP").convertedRates
    }
}