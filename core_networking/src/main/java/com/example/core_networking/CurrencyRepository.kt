package com.example.core_networking

interface CurrencyRepository {
    suspend fun getRatesByBaseCurrency(): Rates
}

class CurrencyRepositoryImpl(private val api: CurrencyApi) : CurrencyRepository {
    override suspend fun getRatesByBaseCurrency(): Rates {
        return api.getRatesByBaseCurrency("GBP").rates
    }
}