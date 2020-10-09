package com.falabella.challenge.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.falabella.challenge.di.AppContainer
import com.falabella.challenge.ui.AppApplication

/**
 * Created by Anibal Cortez on 10/8/20.
 */
abstract class BaseActivity : AppCompatActivity() {

    private val appContainer : AppContainer by lazy {
        AppContainer( (application as AppApplication).appModule(), this)
    }

    fun appContainer(): AppContainer = appContainer
}