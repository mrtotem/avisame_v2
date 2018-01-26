package com.mrtotem.avisame.views.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.mrtotem.avisame.R
import com.mrtotem.avisame.views.fragments.AlertsFragment
import com.mrtotem.avisame.views.fragments.FriendsFragment
import com.mrtotem.avisame.views.fragments.MessagesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        tabLayout = findViewById<View>(R.id.tab) as TabLayout

        toolbar.title = "Avisame!"
        setSupportActionBar(toolbar)

        initTabs()
    }

    private fun initTabs() {

        tabLayout.addTab(tabLayout.newTab().setText("alertar"))
        tabLayout.addTab(tabLayout.newTab().setText("mensajes"))
        tabLayout.addTab(tabLayout.newTab().setText("amigos"))

        openSection(tabLayout.getTabAt(0))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                openSection(tab)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun openSection(tab: TabLayout.Tab?) {

        when (tab?.position) {
            0 -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, AlertsFragment.newInstance("", ""), "alerts_fragment")
                    .commit()
            1 -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, MessagesFragment.newInstance("", ""), "messajes_fragment")
                    .commit()
            2 -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, FriendsFragment.newInstance("", ""), "friends_fragment")
                    .commit()
        }
    }
}
