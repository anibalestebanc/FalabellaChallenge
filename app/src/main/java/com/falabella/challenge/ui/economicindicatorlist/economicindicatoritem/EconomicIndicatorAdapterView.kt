package com.falabella.challenge.ui.economicindicatorlist.economicindicatoritem

import com.falabella.domain.model.EconomicIndicator

interface EconomicIndicatorAdapterView {
    fun getEconomicIndicatorList() : List<EconomicIndicator>
    fun setEconomicIndicatorFiltered(list: List<EconomicIndicator>)
}
