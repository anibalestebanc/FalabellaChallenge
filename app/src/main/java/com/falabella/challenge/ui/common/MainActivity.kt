package com.falabella.challenge.ui.common

import android.os.Bundle
import com.falabella.challenge.R
import com.falabella.challenge.ui.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}