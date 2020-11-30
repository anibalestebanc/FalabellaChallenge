package com.falabella.challenge.ui.economicindicatorlist

import com.falabella.data.repository.EconomicIndicatorRepositoryImpl
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
class EconomicIndicatorModule {

    @Provides
    fun getUseCaseProvider(repositoryImpl: EconomicIndicatorRepositoryImpl) =
        GetEconomicIndicatorListUseCase(repositoryImpl)

    @Provides
    fun economicIndicatorViewModel(
        economicIndicatorUseCase: GetEconomicIndicatorListUseCase,
        dispatcher: CoroutineDispatcher
    ) = EconomicIndicatorViewModel(economicIndicatorUseCase, dispatcher)
}


@Subcomponent(modules = [EconomicIndicatorModule::class])
interface EconomicIndicatorComponent {
    val viewModel: EconomicIndicatorViewModel
}