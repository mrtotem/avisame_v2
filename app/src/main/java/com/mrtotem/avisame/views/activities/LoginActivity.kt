package com.mrtotem.avisame.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mrtotem.avisame.R
import com.mrtotem.avisame.views.fragments.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, LoginFragment())
                .commit()
    }
}
