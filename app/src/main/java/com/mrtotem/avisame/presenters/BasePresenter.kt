package com.mrtotem.avisame.presenters

/**
 * Created by Octavio on 29/01/2018.
 */
public abstract class BasePresenter<V> {

    var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}