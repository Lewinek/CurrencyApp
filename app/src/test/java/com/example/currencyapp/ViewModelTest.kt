package com.example.currencyapp

import androidx.arch.core.executor.ArchTaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class ViewModelTest {
    @BeforeEach
    fun setUp() {
        ArchTaskExecutor.getInstance().setDelegate(FakeMainThreadExecutor)
        Dispatchers.setMain(dispatcher = UnconfinedTestDispatcher())
    }

    @AfterEach
    fun tearDown() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}