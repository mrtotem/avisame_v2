package com.mrtotem.avisame.views.interfaces

import com.mrtotem.avisame.enums.UITypes
import com.mrtotem.avisame.views.activities.MainActivity

/**
 * Created by Octavio on 29/01/2018.
 */
interface BaseMvp {

    fun configToolbar(type: UITypes)

    fun setToolbarTitle(title: String)

    fun getContext(): MainActivity

    fun onDrawerClose()
}