package com.falabella.falabellachallenge.ui

import android.app.Application
import com.falabella.falabellachallenge.di.AppModule

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class AppApplication : Application() {

    private val appModule: AppModule by lazy { AppModule() }

    fun appModule(): AppModule = appModule

}