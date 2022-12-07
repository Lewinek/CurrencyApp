package com.example.currencyapp.converter

import com.example.core_networking.Currency
import java.math.BigDecimal

data class CurrencyDisplayable(
    val name: String,
    val value: BigDecimal,
    val isBaseCurrency: Boolean = false,
    val convertedValue: BigDecimal? = null
) {
    constructor(currency: Currency) : this(
        name = currency.name,
        value = currency.value,
        isBaseCurrency = currency.isBaseCurrency,
        convertedValue = currency.convertedValue
    )
}
