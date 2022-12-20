package com.example.currencyapp.rate

import com.example.core_networking.Currency
import com.example.core_networking.CurrencyRepositoryImpl
import com.example.core_networking.ResultWrapper
import com.example.currencyapp.CurrencyCreator
import com.example.currencyapp.ViewModelTest
import com.example.currencyapp.converter.ConverterViewModel
import com.example.currencyapp.converter.CurrencyDisplayable
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Test

internal class ConverterViewModelTest : ViewModelTest() {
    @Test
    fun `on successful response should add base currency to result then set it in live data`() {
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
        val creator = CurrencyCreator()
        val viewModel = ConverterViewModel(repository, creator)

        //When
        viewModel.getRatesByBaseCurrency("GBP")

        //Then
        viewModel.uiLiveData.value?.currencies shouldBe expectedList.map { CurrencyDisplayable(it) }
    }

    @Test
    fun `should return true on bad request error`() {
        //Prepare
        val repository = mockk<CurrencyRepositoryImpl> {
            coEvery { getRatesByBaseCurrency(any()) } returns ResultWrapper.GenericError(200)
        }
        val creator = CurrencyCreator()
        val viewModel = ConverterViewModel(repository, creator)

        //When
        viewModel.getRatesByBaseCurrency("GBP")

        //Then
        viewModel.uiLiveData.value?.showError shouldBe true
    }

    @Test
    fun `should return true on network error`() {
        //Prepare
        val repository = mockk<CurrencyRepositoryImpl> {
            coEvery { getRatesByBaseCurrency(any()) } returns ResultWrapper.NetworkError
        }
        val creator = CurrencyCreator()
        val viewModel = ConverterViewModel(repository, creator)

        //When
        viewModel.getRatesByBaseCurrency("GBP")

        //Then
        viewModel.uiLiveData.value?.showError shouldBe true
    }
}

@TestOnly
fun Currency.Companion.mock(name: String = "GBP", isBaseCurrency: Boolean = false) = Currency(
    name = name,
    value = 1.toBigDecimal(),
    isBaseCurrency = isBaseCurrency,
    convertedValue = 1.toBigDecimal()
)

