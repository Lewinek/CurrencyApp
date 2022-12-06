package com.example.currencyapp.rate

import com.example.core_networking.Currency
import com.example.core_networking.CurrencyRepositoryImpl
import com.example.core_networking.ResultWrapper
import com.example.currencyapp.ViewModelTest
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Test

internal class RateViewModelTest : ViewModelTest() {
    @Test
    fun `on start it should load data `() {
        val expectedList = listOf(Currency.mock(), Currency.mock(), Currency.mock())
        val repository = mockk<CurrencyRepositoryImpl> {
            coEvery { getRatesByBaseCurrency("") } returns ResultWrapper.Success(expectedList)
        }
        val viewModel = RateViewModel(repository)
        val response = viewModel.getRatesByBaseCurrency("")
        Truth.assertThat(response is ResultWrapper.Success).isTrue()
    }
}

@TestOnly
fun Currency.Companion.mock() = Currency(
    name = "EUR",
    value = 1.toBigDecimal(),
    isBaseCurrency = false,
    convertedValue = 1.toBigDecimal()
)