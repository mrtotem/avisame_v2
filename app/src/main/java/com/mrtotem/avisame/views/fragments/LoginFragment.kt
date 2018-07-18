package com.mrtotem.avisame.views.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.mrtotem.avisame.R
import com.mrtotem.avisame.contracts.OnBoardingContract
import com.mrtotem.avisame.models.views.LoginViewModel
import com.mrtotem.avisame.utils.NavigationUtils
import com.mrtotem.avisame.views.activities.MainActivity
import com.mrtotem.avisame.views.fragments.base.BaseFragment
import com.mrtotem.avisame.widgets.AvisameEditView
import com.mrtotem.avisame.widgets.AvisameTextView

class LoginFragment :
        BaseFragment(),
        OnBoardingContract.Navigator,
        OnBoardingContract.View {

    override fun get(): View? {
        return view
    }

    override fun showLoadingView(show: Boolean) {
        if (show) {
            mPresenterContract.loadingPresenter()?.showLoadingView()
        } else {
            mPresenterContract.loadingPresenter()?.hideLoadingView()
        }
    }

    override fun navigateToRegister() {
        NavigationUtils.replaceFragmentOnActivityBackstack(
                activity.supportFragmentManager,
                RegisterFragment(),
                R.id.main_content)
    }

    override fun navigateToMain() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    lateinit var mRegister: AvisameTextView
    lateinit var mLogin: Button
    lateinit var mUsername: AvisameEditView
    lateinit var mPassword: AvisameEditView
    lateinit var mPresenterContract: OnBoardingContract.Presenter

    private lateinit var loginView: LoginViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mPresenterContract = getContext() as OnBoardingContract.Presenter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater!!.inflate(R.layout.fragment_login, container, false)

        mRegister = v.findViewById(R.id.button_register) as AvisameTextView
        mUsername = v.findViewById(R.id.username) as AvisameEditView
        mPassword = v.findViewById(R.id.password) as AvisameEditView
        mLogin = v.findViewById(R.id.button_login) as Button

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginView = LoginViewModel(this, this)
        setupView()
    }

    private fun setupView() {

        val sb = SpannableStringBuilder("No tenés una cuenta? Registrate acá")

        sb.setSpan(ForegroundColorSpan(
                resources.getColor(R.color.colorToolbar)),
                21, mRegister.text.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        sb.setSpan(
                StyleSpan(android.graphics.Typeface.NORMAL),
                21,
                mRegister.text.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        mRegister.text = sb

        mRegister.setOnClickListener {
            navigateToRegister()
        }

        mLogin.setOnClickListener {
            loginView.login()?.let {
                this.view?.let { view ->
                    Snackbar.make(view, it, Snackbar.LENGTH_SHORT)
                            .show()
                }
            }
        }

        mUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let { loginView.updateUsername(it) }
            }
        })

        mPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let { loginView.updatePassword(it) }
            }
        })
    }
}
