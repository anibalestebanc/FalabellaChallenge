package com.falabella.falabellachallenge.ui.common

import androidx.fragment.app.Fragment
import com.falabella.falabellachallenge.di.AppContainer

/**
 * Created by Anibal Cortez on 10/8/20.
 */
abstract class BaseFragment : Fragment() {

    fun appContainer(): AppContainer = (requireActivity() as BaseActivity).appContainer()

}