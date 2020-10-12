package com.falabella.challenge.ui.economicindicatorlist.economicindicatoritem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falabella.domain.model.EconomicIndicator
import com.falabella.challenge.R

class EconomicIndicatorAdapter(private val listener: (EconomicIndicator) -> Unit) :
    RecyclerView.Adapter<EconomicIndicatorAdapter.ViewHolder>(), Filterable,
    EconomicIndicatorAdapterView, EconomicIndicatorSortedByView {

    private var economicIndicators: List<EconomicIndicator> = emptyList()
    private var economicIndicatorFiltered: List<EconomicIndicator> = economicIndicators

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_economic_indicator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val economicIndicator = economicIndicatorFiltered.get(position)
        holder.itemName.text = economicIndicator.name
        holder.itemUnitOfMeasure.text = economicIndicator.unitOfMeasure
        holder.itemValue.text = economicIndicator.value
        holder.itemView.setOnClickListener {
            listener(economicIndicator)
        }
    }

    override fun getItemCount(): Int = economicIndicatorFiltered.size

    override fun getFilter(): Filter = EconomicIndicatorFilter(this)

    override fun getEconomicIndicatorList(): List<EconomicIndicator> = economicIndicators

    override fun setEconomicIndicatorFiltered(list: List<EconomicIndicator>) {
        economicIndicatorFiltered = list
        notifyDataSetChanged()
    }
    override fun setEconomicIndicatorSorted(list: List<EconomicIndicator>) {
        economicIndicatorFiltered = list
        notifyDataSetChanged()
    }

    fun setEconomicIndicatorList(list: List<EconomicIndicator>) {
        economicIndicators = list
        economicIndicatorFiltered = economicIndicators
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.nameTextView)
        val itemUnitOfMeasure: TextView = view.findViewById(R.id.unitOfMeasureTextView)
        val itemValue: TextView = view.findViewById(R.id.valueTextView)
    }
}