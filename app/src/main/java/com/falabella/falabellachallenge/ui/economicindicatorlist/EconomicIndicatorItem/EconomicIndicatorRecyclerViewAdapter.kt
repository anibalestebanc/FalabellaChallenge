package com.falabella.falabellachallenge.ui.economicindicatorlist.EconomicIndicatorItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falabella.domain.model.EconomicIndicator
import com.falabella.falabellachallenge.R

class EconomicIndicatorRecyclerViewAdapter (private val listener : (EconomicIndicator) ->Unit, private val economicIndicatorList : List<EconomicIndicator>)
    : RecyclerView.Adapter<EconomicIndicatorRecyclerViewAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_economic_indicator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val economicIndicator = economicIndicatorList.get(position)
        holder.itemName.text = economicIndicator.name
        holder.itemUnitOfMeasure.text = economicIndicator.unitOfMeasure
        holder.itemValue.text = economicIndicator.value
        holder.itemView.setOnClickListener {
            listener(economicIndicator)
        }
    }

    override fun getItemCount(): Int = economicIndicatorList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.nameTextView)
        val itemUnitOfMeasure: TextView = view.findViewById(R.id.unitOfMeasureTextView)
        val itemValue: TextView = view.findViewById(R.id.valueTextView)
    }

}