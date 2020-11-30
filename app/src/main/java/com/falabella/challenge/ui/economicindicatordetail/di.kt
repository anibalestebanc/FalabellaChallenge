package com.falabella.challenge.ui.economicindicatordetail

import com.falabella.data.repository.EconomicIndicatorRepositoryImpl
import com.falabella.domain.usecase.GetEconomicIndicatorDetailUseCase
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
class EconomicIndicatorDetailModule() {

    @Provides
    fun getUseCaseProvider(repositoryImpl: EconomicIndicatorRepositoryImpl) =
        GetEconomicIndicatorDetailUseCase(repositoryImpl)

    @Provides
    fun economicIndicatorDetailViewModel(
        economicIndicatorDetailUseCase: GetEconomicIndicatorDetailUseCase,
        dispatcher: CoroutineDispatcher
    ) = EconomicIndicatorDetailViewModel(
        economicIndicatorDetailUseCase,
        dispatcher)

}

@Subcomponent(modules = [EconomicIndicatorDetailModule::class])
interface EconomicIndicatorDetailComponent {
    val viewModel: EconomicIndicatorDetailViewModel
}