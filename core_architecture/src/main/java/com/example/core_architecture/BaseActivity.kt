package com.example.core_architecture

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class BaseActivity<UM : UiModel, VM : BaseViewModel<UM>>(
    @LayoutRes val layoutRes: Int
) : AppCompatActivity() {

    protected abstract val viewModel: VM

    protected abstract fun render(model: UM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.run {
            uiLiveData.observe(this@BaseActivity, Observer { render(it) })
        }
    }