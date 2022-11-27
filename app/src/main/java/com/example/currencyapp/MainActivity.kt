package com.example.currencyapp

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.core_architecture.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<MainActivityUiModel, MainActivityViewModel>(R.layout.activity_main) {
    override val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findNavController(R.id.navHostFragment).navigate(R.id.actionNavigateToRate)
    }

    override fun render(model: MainActivityUiModel) {}
}