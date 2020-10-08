package com.falabella.falabellachallenge.common

import android.content.Context
import android.net.ConnectivityManager

class ConnectionHelper(private val context : Context) {

    fun isConnected() : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
