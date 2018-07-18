package com.mrtotem.avisame.views.activities

import android.os.Bundle
import com.mrtotem.avisame.R
import com.mrtotem.avisame.contracts.OnBoardingContract
import com.mrtotem.avisame.presenters.LoadingPresenter
import com.mrtotem.avisame.utils.NavigationUtils
import com.mrtotem.avisame.views.activities.base.BaseActivity
import com.mrtotem.avisame.views.fragments.LoginFragment

class LoginActivity : BaseActivity(), OnBoardingContract.Presenter {

    lateinit var loadingPresenter: LoadingPresenter

    override fun loadingPresenter(): LoadingPresenter? {
        return loadingPresenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //setup nav presenter
        loadingPresenter = LoadingPresenter()
        loadingPresenter.attachView(this)

        NavigationUtils.replaceFragmentOnActivity(
                supportFragmentManager,
                LoginFragment(),
                R.id.main_content
        )
    }
}
