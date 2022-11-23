package com.example.currencyapp

import com.example.core_architecture.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<MainActivityUiModel, MainActivityViewModel>(R.layout.activity_main) {
    override val viewModel: MainActivityViewModel by viewModel()

    override fun render(model: MainActivityUiModel) {}
}