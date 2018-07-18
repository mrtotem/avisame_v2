package com.mrtotem.avisame.contracts

class OnBoardingContract {

    interface View {

    }

    interface Presenter {

    }

    interface Navigator {
        fun navigateToMain()
        fun navigateToRegister()
    }
}