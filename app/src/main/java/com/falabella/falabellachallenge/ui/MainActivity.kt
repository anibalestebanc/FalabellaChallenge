package com.falabella.falabellachallenge.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.falabella.falabellachallenge.R
import com.falabella.falabellachallenge.ui.common.BaseActivity
import com.falabella.falabellachallenge.ui.economicindicatordetail.EconomicIndicatorDetailFragment
import com.falabella.falabellachallenge.ui.economicindicatorlist.EconomicIndicatorFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}