package com.falabella.challenge.di.component

import android.app.Application
import com.falabella.challenge.di.module.AppModule
import com.falabella.challenge.di.module.DataModule
import com.falabella.challenge.ui.economicindicatordetail.EconomicIndicatorDetailComponent
import com.falabella.challenge.ui.economicindicatordetail.EconomicIndicatorDetailModule
import com.falabella.challenge.ui.economicindicatorlist.EconomicIndicatorComponent
import com.falabella.challenge.ui.economicindicatorlist.EconomicIndicatorModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface ApplicationComponent {


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ApplicationComponent
    }

    fun economicIndicatorComponent(module: EconomicIndicatorModule)
            : EconomicIndicatorComponent

    fun economicIndicatorDetailComponent(module: EconomicIndicatorDetailModule)
            : EconomicIndicatorDetailComponent

}