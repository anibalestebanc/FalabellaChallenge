package com.falabella.challenge.ui.economicindicatorlist.economicindicatoritem

import android.widget.Filter
import com.falabella.domain.model.EconomicIndicator

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class EconomicIndicatorFilter(private val filteredView: EconomicIndicatorAdapterView) : Filter() {
    override fun performFiltering(constraint: CharSequence?): FilterResults {

        val filterResults = FilterResults()

        if (constraint.isNullOrEmpty()) {
            filterResults.count = filteredView.getEconomicIndicatorList().size
            filterResults.values = filteredView.getEconomicIndicatorList()
            return filterResults
        }

        val charSearch = constraint.toString().toLowerCase()
        val filteredList = mutableListOf<EconomicIndicator>()

        for (indicator: EconomicIndicator in filteredView.getEconomicIndicatorList()) {
            val code = indicator.code.toLowerCase()
            val name = indicator.name.toLowerCase()
            if (code.contains(charSearch) || name.contains(charSearch))
                filteredList.add(indicator)
        }

        filterResults.count = filteredList.size
        filterResults.values = filteredList

        return filterResults
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        if (results?.count == 0) filteredView.setEconomicIndicatorFiltered(emptyList())
        else filteredView.setEconomicIndicatorFiltered(results?.values as MutableList<EconomicIndicator>)
    }
}