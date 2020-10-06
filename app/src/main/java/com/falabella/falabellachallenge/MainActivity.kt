package com.falabella.falabellachallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.falabella.falabellachallenge.ui.economicindicatorlist.EconomicIndicatorFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ShowEconomicIndicatorList()
    }

    private fun ShowEconomicIndicatorList(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = EconomicIndicatorFragment()
        fragmentTransaction.add(R.id.main_container, fragment)
        fragmentTransaction.commit()
    }
}