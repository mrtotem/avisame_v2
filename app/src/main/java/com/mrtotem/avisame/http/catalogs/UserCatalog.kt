package com.mrtotem.avisame.http.catalogs

import com.mrtotem.avisame.models.User
import com.mrtotem.avisame.models.responses.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCatalog : Callback<UserResponse> {

    val userSubject: PublishSubject<Boolean> = PublishSubject.create()
    var user: User? = null


    override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
        userSubject.onNext(false)
    }

    override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
        user = response?.body()?.getUser()

        userSubject.onNext(true)
    }

    fun subscribeToUserSubject(consumer: Consumer<Boolean>) {

        userSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer)
    }
}