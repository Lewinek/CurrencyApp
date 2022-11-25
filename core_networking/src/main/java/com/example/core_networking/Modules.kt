package com.example.core_networking

import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkingModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(ApiConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    }

    single { get<Retrofit>().create(CurrencyApi::class.java) }
}