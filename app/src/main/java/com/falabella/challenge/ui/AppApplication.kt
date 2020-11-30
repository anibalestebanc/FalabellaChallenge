package com.falabella.challenge.ui

import android.app.Application
import com.falabella.challenge.di.component.ApplicationComponent
import com.falabella.challenge.di.component.DaggerApplicationComponent

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class AppApplication : Application() {

    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.factory().create(this)
    }

}