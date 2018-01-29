package com.mrtotem.avisame.views.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.mrtotem.avisame.R
import com.mrtotem.avisame.presenters.MainPresenter
import com.mrtotem.avisame.views.fragments.AlertsFragment
import com.mrtotem.avisame.views.fragments.FriendsFragment
import com.mrtotem.avisame.views.fragments.MessagesFragment
import com.mrtotem.avisame.views.interfaces.BaseMvp

class MainActivity : BaseMvp, AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        mTabLayout = findViewById<View>(R.id.tab) as TabLayout

        setSupportActionBar(mToolbar)

        initTabs()
        startsMainPresenter()
    }

    private fun initTabs() {

        mTabLayout.addTab(mTabLayout.newTab().setIcon(resources.getDrawable(R.mipmap.action)))
        mTabLayout.addTab(mTabLayout.newTab().setIcon(resources.getDrawable(R.mipmap.message_unselected)))
        mTabLayout.addTab(mTabLayout.newTab().setIcon(resources.getDrawable(R.mipmap.friends_unselected)))

        openSection(mTabLayout.getTabAt(0))

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> tab.icon = resources.getDrawable(R.mipmap.action)
                    1 -> tab.icon = resources.getDrawable(R.mipmap.message)
                    2 -> tab.icon = resources.getDrawable(R.mipmap.friends)
                }
                openSection(tab)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> tab.icon = resources.getDrawable(R.mipmap.action_unselected)
                    1 -> tab.icon = resources.getDrawable(R.mipmap.message_unselected)
                    2 -> tab.icon = resources.getDrawable(R.mipmap.friends_unselected)
                }
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

    private fun startsMainPresenter() {

        mMainPresenter = MainPresenter()
    }

    override fun configToolbar(backButton: Boolean) {

        if (backButton) {
            mToolbar.navigationIcon = resources.getDrawable(R.mipmap.back_arrow)
            mToolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        } else {
            mToolbar.navigationIcon = null
        }
    }

    override fun setToolbarTitle(title: String) {
        mToolbar.title = title
    }
}
