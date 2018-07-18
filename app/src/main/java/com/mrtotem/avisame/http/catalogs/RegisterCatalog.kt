package com.mrtotem.avisame.http.catalogs

import com.mrtotem.avisame.models.responses.RegisterResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCatalog : Callback<RegisterResponse> {

    val userSubject: PublishSubject<Boolean> = PublishSubject.create()
    var username = ""
    var email = ""
    var password = ""

    override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
        userSubject.onNext(false)
    }

    override fun onResponse(call: Call<RegisterResponse>?, response: Response<RegisterResponse>?) {
        userSubject.onNext(true)
    }

    fun subscribe(consumer: Consumer<Boolean>) {

        userSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer)
    }
}