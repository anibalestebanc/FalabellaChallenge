package com.falabella.falabellachallenge.ui

import android.os.Bundle
import com.falabella.falabellachallenge.R
import com.falabella.falabellachallenge.ui.common.BaseActivity
import com.falabella.falabellachallenge.ui.economicindicatordetail.EconomicIndicatorDetailFragment
import com.falabella.falabellachallenge.ui.economicindicatorlist.EconomicIndicatorFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showEconomicIndicatorList()
    }

    private fun showEconomicIndicatorList(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = EconomicIndicatorFragment()
        fragmentTransaction.add(R.id.main_container, fragment)
        fragmentTransaction.commit()
    }

    fun showEconomicIndicatorDetail( code: String, name : String, value: String){
        val fragmentManager  = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = EconomicIndicatorDetailFragment.newInstance(code, name, value)
        fragmentTransaction.replace(R.id.main_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}