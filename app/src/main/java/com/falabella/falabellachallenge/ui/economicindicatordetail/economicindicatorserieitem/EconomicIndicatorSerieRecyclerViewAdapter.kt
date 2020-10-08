package com.falabella.falabellachallenge.ui.economicindicatordetail.economicindicatorserieitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falabella.domain.model.Serie
import com.falabella.falabellachallenge.R

class EconomicIndicatorSerieRecyclerViewAdapter
    : RecyclerView.Adapter<EconomicIndicatorSerieRecyclerViewAdapter.ViewHolder>() {

    private var economicIndicatorSerieList : List<Serie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_economic_indicator_serie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val serieItem = economicIndicatorSerieList.get(position)
        holder.itemDate.text = serieItem.date
        holder.itemValue.text = serieItem.value
    }

    override fun getItemCount(): Int = economicIndicatorSerieList.size
    fun setEconomicIndicatorSerieList(serieList: List<Serie>) {
        economicIndicatorSerieList = serieList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemDate: TextView = view.findViewById(R.id.date_serie_item)
        val itemValue: TextView = view.findViewById(R.id.value_serie_item)
    }
}