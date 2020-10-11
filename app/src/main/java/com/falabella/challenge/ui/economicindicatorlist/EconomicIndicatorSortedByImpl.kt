package com.falabella.challenge.ui.economicindicatorlist

import com.falabella.challenge.ui.economicindicatorlist.economicindicatoritem.EconomicIndicatorSortedByView



/**
 * Created by Anibal Cortez on 10/11/20.
 */
class EconomicIndicatorSortedByImpl(private val sortedBy : EconomicIndicatorSortedByView) {

    fun clearSortedBy() {
        val sortedList = sortedBy.getEconomicIndicatorList()
        sortedBy.setEconomicIndicatorSorted(sortedList)
    }

    fun sortedByAsc() {
        val sortedList = sortedBy.getEconomicIndicatorList().sortedBy { it.name }
        sortedBy.setEconomicIndicatorSorted(sortedList)
    }

    fun sortedByDes() {
        val sortedList = sortedBy.getEconomicIndicatorList().sortedByDescending { it.name }
        sortedBy.setEconomicIndicatorSorted(sortedList)
    }
}