package com.example.core_networking

import java.math.BigDecimal

data class CurrencyRemote(
    val name : String,
    val value: BigDecimal,
    val isBaseCurrency: Boolean = false,
    val convertedValue: BigDecimal? = null
) {
    fun toCurrency() = Currency(
        name = name,
        value = value
    )
}
