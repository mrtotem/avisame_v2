package com.mrtotem.avisame.presenters

import com.mrtotem.avisame.views.interfaces.NavigatorMvp

/**
 * Created by Octavio on 29/01/2018.
 */
class LoadingPresenter : BasePresenter<NavigatorMvp>() {

    fun showLoadingView() {
        view?.showLoadingView()
    }

    fun hideLoadingView() {
        view?.hideLoadingView()
    }
}