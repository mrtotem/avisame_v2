package com.mrtotem.avisame.views.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.mrtotem.avisame.R
import com.mrtotem.avisame.enums.UITypes
import com.mrtotem.avisame.presenters.MainPresenter
import com.mrtotem.avisame.presenters.MessagesPresenter
import com.mrtotem.avisame.views.fragments.AlertsFragment
import com.mrtotem.avisame.views.fragments.FriendsFragment
import com.mrtotem.avisame.views.fragments.MessagesFragment
import com.mrtotem.avisame.views.interfaces.BaseMvp

class MainActivity : AppCompatActivity(), BaseMvp {

    private lateinit var mToolbar: Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mNavigationView: RecyclerView
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mMainPresenter: MainPresenter
    private lateinit var mMessagesPresenter: MessagesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        mTabLayout = findViewById<View>(R.id.tab) as TabLayout
        mDrawerLayout = findViewById<DrawerLayout>(R.id.drawer) as DrawerLayout
        mNavigationView = findViewById<RecyclerView>(R.id.nav_view) as RecyclerView

        setSupportActionBar(mToolbar)

        initMainPresenter()
        initMessagesPresenter()

        initNavView()
        initTabs()
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initNavView() {
        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mNavigationView.adapter = mMainPresenter.buildNavigationView()
    }

    private fun initTabs() {

        mTabLayout.addTab(mTabLayout.newTab().setIcon(getDrawable(R.mipmap.action)))
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getDrawable(R.mipmap.message_unselected)))
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getDrawable(R.mipmap.friends_unselected)))

        openSection(mTabLayout.getTabAt(0))

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> tab.icon = getDrawable(R.mipmap.action)
                    1 -> tab.icon = getDrawable(R.mipmap.message)
                    2 -> tab.icon = getDrawable(R.mipmap.friends)
                }
                openSection(tab)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> tab.icon = getDrawable(R.mipmap.action_unselected)
                    1 -> tab.icon = getDrawable(R.mipmap.message_unselected)
                    2 -> tab.icon = getDrawable(R.mipmap.friends_unselected)
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

    private fun initMainPresenter() {

        mMainPresenter = MainPresenter()
        mMainPresenter.attachView(this)
    }

    private fun initMessagesPresenter() {

        mMessagesPresenter = MessagesPresenter()
        mMessagesPresenter.attachView(this)
    }

    override fun onDrawerClose() {
        mDrawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun getContext(): MainActivity {
        return this
    }

    override fun configToolbar(type: UITypes) {

        when (type) {
            UITypes.WITH_BACK_BUTTON -> {
                mToolbar.navigationIcon = getDrawable(R.mipmap.back_arrow)
                mToolbar.setNavigationOnClickListener {
                    onBackPressed()
                }
            }
            UITypes.WITH_NAVIGATION -> {
                mToolbar.navigationIcon = getDrawable(R.mipmap.burger)
                mToolbar.setNavigationOnClickListener {
                    if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mDrawerLayout.openDrawer(GravityCompat.START)
                    }
                }
            }

        }
    }

    override fun setToolbarTitle(title: String) {
        mToolbar.title = title
    }

    override fun getMessagePresenter(): MessagesPresenter {
        return mMessagesPresenter
    }
}
