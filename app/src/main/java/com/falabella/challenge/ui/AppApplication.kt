package com.falabella.challenge.ui

import android.app.Application
import com.falabella.challenge.di.AppModule

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class AppApplication : Application() {

    private val appModule: AppModule by lazy { AppModule() }

    fun appModule(): AppModule = appModule

}