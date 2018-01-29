package com.mrtotem.avisame.views.fragments.base

import android.content.Context
import android.support.v4.app.Fragment
import com.mrtotem.avisame.views.interfaces.BaseMvp

/**
 * Created by Octavio on 29/01/2018.
 */
abstract class BaseFragment : Fragment() {

    protected var mHomeMvp: BaseMvp? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mHomeMvp = activity as BaseMvp
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        mHomeMvp!!.configToolbar(false)
    }

    override fun onDetach() {
        super.onDetach()
        mHomeMvp = null
    }
}