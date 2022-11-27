package com.example.core_networking

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkingModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(ApiConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(
                RetrofitClientInstance.unSafeOkHttpClient()
                    .addInterceptor(get<Interceptor>())
                    .build()
            ).build()
    }

    single {
        Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    }

    single<Interceptor> {
        HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
    }

    single { get<Retrofit>().create(CurrencyApi::class.java) }

    factory<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
}