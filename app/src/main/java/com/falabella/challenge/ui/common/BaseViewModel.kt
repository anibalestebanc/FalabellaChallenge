package com.falabella.challenge.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Anibal Cortez on 10/11/20.
 */
abstract class BaseViewModel(private val uiDispacher : CoroutineDispatcher)
    : ViewModel(), Scope by Scope.Impl(uiDispacher){

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}