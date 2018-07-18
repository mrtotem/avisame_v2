package com.mrtotem.avisame.contracts

import com.mrtotem.avisame.presenters.LoadingPresenter

open class BaseContract {

    interface Presenter {
        fun loadingPresenter(): LoadingPresenter?
    }
}