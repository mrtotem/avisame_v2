package com.mrtotem.avisame.http.catalogs

import com.mrtotem.avisame.models.responses.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCatalog : Callback<LoginResponse> {

    val userSubject: PublishSubject<Boolean> = PublishSubject.create()
    var username = ""
    var email = ""
    var password = ""

    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
        userSubject.onNext(false)
    }

    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
        userSubject.onNext(true)
    }

    fun subscribeToUserSubject(consumer: Consumer<Boolean>) {

        userSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer)
    }
}