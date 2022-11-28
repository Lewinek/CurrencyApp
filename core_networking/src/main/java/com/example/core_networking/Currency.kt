package com.example.core_networking

import java.math.BigDecimal

data class Currency(
    val name : String,
    val rate: BigDecimal,
    val isBaseCurrency: Boolean = false,
    val convertedValue: BigDecimal? = null
)
