package com.mrtotem.avisame.contracts

class OnBoardingContract {

    interface View {
        fun get(): android.view.View?
    }

    interface Presenter : BaseContract.Presenter

    interface Navigator {
        fun navigateToMain()
        fun navigateToRegister()
        fun showLoadingView(show: Boolean)
    }

    companion object {
        const val LOGIN_RESPONSE: String = "LOGIN_RESPONSE"
        const val REGISTER_RESPONSE: String = "REGISTER_RESPONSE"
        const val GET_USER_RESPONSE: String = "GET_USER_RESPONSE"
    }
}