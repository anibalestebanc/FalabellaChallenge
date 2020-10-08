package com.falabella.falabellachallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.falabella.falabellachallenge.ui.economicindicatordetail.EconomicIndicatorDetailFragment
import com.falabella.falabellachallenge.ui.economicindicatorlist.EconomicIndicatorFragment

class MainActivity : AppCompatActivity() {
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

    fun showEconomicIndicatorDetail( code: String, name : String){
        val fragmentManager  = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = EconomicIndicatorDetailFragment.newInstance(code, name)
        fragmentTransaction.replace(R.id.main_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}