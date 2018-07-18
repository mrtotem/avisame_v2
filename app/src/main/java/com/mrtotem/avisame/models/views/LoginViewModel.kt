package com.mrtotem.avisame.models.views

import android.util.Log
import com.mrtotem.avisame.contracts.OnBoardingContract
import com.mrtotem.avisame.managers.UserOperationsManager
import com.mrtotem.avisame.models.responses.SubjectItem
import io.reactivex.functions.Consumer

class LoginViewModel(var view: OnBoardingContract.View, private val navigator: OnBoardingContract.Navigator) {

    var username = ""
    var password = ""

    var loginObserver = Consumer<SubjectItem<String>> {

        it.error?.let {
            Log.e("USER", it.error)
            return@Consumer
        }

        it.item?.let { data ->
            Log.d("USER", "LOGIN SUCCESS")
            when (it.key) {
                UserOperationsManager.LOGIN_RESPONSE -> /*getUser()*/ {
                    navigator.showLoadingView(false)
                    navigator.navigateToMain()
                }
                UserOperationsManager.GET_USER_RESPONSE -> Log.d("USER", "GET USER SUCCESS")
                else -> {
                }
            }
            return@Consumer
        }
    }

    fun init() {

    }

    fun login(): String? {
        when {
            username.isEmpty() -> {
                return "Por favor completá tu nombre de usuario"
            }
            password.isEmpty() -> {
                return "Por favor completá tu contraseña"
            }
            else -> {
                navigator.showLoadingView(true)
                UserOperationsManager.getInstance().callToLogin(view.get(), username, password, loginObserver)
            }
        }

        return null
    }

    fun getUser() {
        UserOperationsManager.getInstance().getLoggedUser(view.get(), loginObserver)
    }

    fun updateUsername(user: CharSequence?) {
        username = user.toString()
    }

    fun updatePassword(pwd: CharSequence) {
        password = pwd.toString()
    }
}