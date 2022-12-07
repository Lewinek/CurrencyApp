package com.example.currencyapp

import com.example.currencyapp.converter.CurrencyDisplayable

class CurrencyCreator {
    fun createBaseCurrency(currencyName: String): CurrencyDisplayable {
        return CurrencyDisplayable(
            currencyName,
            1.toBigDecimal(),
            true,
            1.toBigDecimal()
        )
    }
}