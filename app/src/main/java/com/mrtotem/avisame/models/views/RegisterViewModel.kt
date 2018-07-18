package com.mrtotem.avisame.models.views

import android.util.Log
import com.mrtotem.avisame.contracts.OnBoardingContract
import com.mrtotem.avisame.contracts.OnBoardingContract.Companion.GET_USER_RESPONSE
import com.mrtotem.avisame.contracts.OnBoardingContract.Companion.LOGIN_RESPONSE
import com.mrtotem.avisame.managers.UserOperationsManager
import com.mrtotem.avisame.models.responses.SubjectItem
import io.reactivex.functions.Consumer

class RegisterViewModel(var view: OnBoardingContract.View, private val navigator: OnBoardingContract.Navigator) {

    var email = ""
    var username = ""
    var password = ""
    var passwordConfirm = ""

    private var loginObserver = Consumer<SubjectItem<String>> {

        it.error?.let {
            Log.e("USER", it.error)
            return@Consumer
        }

        it.item?.let { data ->
            Log.d("USER", "LOGIN SUCCESS")
            when (it.key) {
                LOGIN_RESPONSE -> /*getUser()*/ navigator.navigateToMain()
                GET_USER_RESPONSE -> Log.d("USER", "GET USER SUCCESS")
                else -> {
                }
            }
            return@Consumer
        }
    }

    fun init() {

    }

    fun register(): String? {
        when {
            username.isEmpty() -> {
                return "Por favor completá tu nombre de usuario"
            }
            email.isEmpty() -> {
                return "Por favor completá tu email"
            }
            password.isEmpty() -> {
                return "Por favor completá tu contraseña"
            }
            passwordConfirm.isEmpty() -> {
                return "Por favor completá tu contraseña para confirmar"
            }
            passwordConfirm != password -> {
                return "Las contraseñas no coinciden"
            }
            else -> UserOperationsManager.getInstance().callToRegister(view.get(), username, email, password, loginObserver)
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