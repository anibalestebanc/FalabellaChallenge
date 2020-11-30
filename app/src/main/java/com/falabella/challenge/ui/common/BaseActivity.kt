package com.falabella.challenge.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.falabella.challenge.di.component.ApplicationComponent
import com.falabella.challenge.ui.AppApplication

/**
 * Created by Anibal Cortez on 10/8/20.
 */
abstract class BaseActivity : AppCompatActivity() {
    fun appComponent(): ApplicationComponent = (application as AppApplication).component
}