package com.mrtotem.avisame.views.interfaces

import com.mrtotem.avisame.enums.UITypes

/**
 * Created by Octavio on 29/01/2018.
 */
interface BaseMvp {

    fun configToolbar(type: UITypes)

    fun setToolbarTitle(title: String)
}