package com.falabella.challenge.ui.common

import android.view.View

/**
 * Created by Anibal Cortez on 10/11/20.
 */
class ErrorViewHelper(
    private val contentView: View,
    private val errorView: View,
    private val connectionView: View,
    private val loadingView: View
) {

    private var currentView: View? = null

    fun showContent() = showNewView(contentView)

    fun showConnection() = showNewView(connectionView)

    fun showLoading() = showNewView(loadingView)

    fun showError() = showNewView(errorView)

    private fun hideCurrentView() {
        currentView?.visibility = View.GONE
    }

    private fun showNewView(newView: View) {
        hideCurrentView()
        newView.visibility = View.VISIBLE
        currentView = newView
    }


}