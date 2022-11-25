package com.example.core_networking

import org.koin.dsl.module
import retrofit2.Retrofit

val networkingModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://hiring.revolut.codes/api/android/")
            .build()
    }
}