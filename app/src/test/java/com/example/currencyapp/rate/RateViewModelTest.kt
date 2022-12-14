package com.example.currencyapp.rate

import com.example.core_networking.Currency
import com.example.core_networking.CurrencyRepositoryImpl
import com.example.core_networking.ResultWrapper
import com.example.currencyapp.ViewModelTest
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Test

internal class RateViewModelTest : ViewModelTest() {
    @Test
    fun `should set result in live data on successful response`() {
        //Prepare
        val givenList = listOf(
            Currency.mock(name = "BRL"),
            Currency.mock(name = "EUR"),
            Currency.mock(name = "PLN")
        )
        val expectedList = listOf(
            Currency.mock(name = "GBP", isBaseCurrency = true),
            Currency.mock(name = "BRL"),
            Currency.mock(name = "EUR"),
            Currency.mock(name = "PLN")
        )
        val repository = mockk<CurrencyRepositoryImpl> {
            coEvery { getRatesByBaseCurrency("GBP") } returns ResultWrapper.Success(givenList)
        }
        val viewModel = RateViewModel(repository)

        //When
        viewModel.getRatesByBaseCurrency("GBP")

        //Then
        viewModel.uiLiveData.value?.rates shouldBe expectedList
    }
}

@TestOnly
fun Currency.Companion.mock(name: String = "GBP", isBaseCurrency: Boolean = false) = Currency(
    name = name,
    value = 1.toBigDecimal(),
    isBaseCurrency = isBaseCurrency,
    convertedValue = 1.toBigDecimal()
)