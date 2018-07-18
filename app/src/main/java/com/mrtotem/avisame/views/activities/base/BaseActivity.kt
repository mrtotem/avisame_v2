package com.mrtotem.avisame.views.activities.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout
import com.mrtotem.avisame.R
import com.mrtotem.avisame.presenters.LoadingPresenter
import com.mrtotem.avisame.views.interfaces.NavigatorMvp

open class BaseActivity : AppCompatActivity(), NavigatorMvp {

    override fun hideLoadingView() {
        findViewById<RelativeLayout>(R.id.loading_view)?.visibility = View.GONE
    }

    override fun showLoadingView() {
        findViewById<RelativeLayout>(R.id.loading_view)?.visibility = View.VISIBLE
    }

    lateinit var navPresenter: LoadingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navPresenter = LoadingPresenter()
        navPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navPresenter.detachView()
    }
}
