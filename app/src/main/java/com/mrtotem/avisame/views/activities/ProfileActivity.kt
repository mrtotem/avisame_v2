package com.mrtotem.avisame.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mrtotem.avisame.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)
    }
}
