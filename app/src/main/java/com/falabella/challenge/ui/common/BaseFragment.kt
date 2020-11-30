package com.falabella.challenge.ui.common

import androidx.fragment.app.Fragment
import com.falabella.challenge.di.component.ApplicationComponent

/**
 * Created by Anibal Cortez on 10/8/20.
 */
abstract class BaseFragment : Fragment() {

    fun appComponent(): ApplicationComponent = (requireActivity() as BaseActivity).appComponent()

}