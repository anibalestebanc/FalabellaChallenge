package com.falabella.challenge.ui.economicindicatorlist.economicindicatoritem

import com.falabella.domain.model.EconomicIndicator

/**
 * Created by Anibal Cortez on 10/11/20.
 */
interface EconomicIndicatorSortedByView {
    fun setEconomicIndicatorSorted(list: List<EconomicIndicator>)
    fun getEconomicIndicatorList(): List<EconomicIndicator>
}