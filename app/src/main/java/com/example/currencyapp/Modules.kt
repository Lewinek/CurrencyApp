package com.example.currencyapp

import com.example.currencyapp.rate.ConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { ConverterViewModel(get()) }
}