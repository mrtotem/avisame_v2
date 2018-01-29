package com.mrtotem.avisame.presenters

import com.mrtotem.avisame.enums.NavOptions
import com.mrtotem.avisame.views.adapters.NavigationViewAdapter
import com.mrtotem.avisame.views.interfaces.BaseMvp

/**
 * Created by Octavio on 29/01/2018.
 */
class MainPresenter : BasePresenter<BaseMvp>(), NavigationViewAdapter.OnNavigationClickListener {

    lateinit var adapter: NavigationViewAdapter

    override fun onClickListener(option: NavOptions) {
        when (option) {
            NavOptions.PROFILE -> {
                view!!.onDrawerClose()
            }

            NavOptions.LOGOUT -> {
                view!!.onDrawerClose()
            }
        }
    }

    fun buildNavigationView(): NavigationViewAdapter {

        adapter = NavigationViewAdapter(view!!.getContext(), this)
        adapter.configNavView()
        return adapter
    }
}