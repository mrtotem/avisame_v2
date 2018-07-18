package com.mrtotem.avisame.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.mrtotem.avisame.R

object NavigationUtils {

    fun addFragmentToActivity() {

    }

    fun replaceFragmentOnActivity(manager: FragmentManager, fragment: Fragment, container: Int) {
        manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(container, fragment)
                .commitAllowingStateLoss()
    }

    fun replaceFragmentOnActivityBackstack(manager: FragmentManager, fragment: Fragment, container: Int) {
        manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .addToBackStack(fragment::class.java.name)
                .replace(container, fragment)
                .commitAllowingStateLoss()
    }
}