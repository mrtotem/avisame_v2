package com.mrtotem.avisame.views.activities

import android.os.Bundle
import com.mrtotem.avisame.R
import com.mrtotem.avisame.utils.NavigationUtils
import com.mrtotem.avisame.views.activities.base.BaseActivity
import com.mrtotem.avisame.views.fragments.LoginFragment

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        NavigationUtils.replaceFragmentOnActivity(
                supportFragmentManager,
                LoginFragment(),
                R.id.main_content
        )
    }
}
