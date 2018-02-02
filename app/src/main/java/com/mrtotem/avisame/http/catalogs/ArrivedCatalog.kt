package com.mrtotem.avisame.http.catalogs

import com.mrtotem.avisame.models.Message
import com.mrtotem.avisame.models.responses.ArrivalsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Octavio on 01/02/2018.
 */
class ArrivedCatalog : Callback<ArrivalsResponse> {

    val arrivedSubject: PublishSubject<Boolean> = PublishSubject.create()
    var arrivedMessages: ArrayList<Message>? = arrayListOf()

    override fun onFailure(call: Call<ArrivalsResponse>?, t: Throwable?) {
        arrivedSubject.onNext(false)
    }

    override fun onResponse(call: Call<ArrivalsResponse>?, response: Response<ArrivalsResponse>?) {
        arrivedMessages = response?.body()?.getArrivals()
        arrivedSubject.onNext(true)
    }

    fun subscribeToArrivalsSubject(consumer: Consumer<Boolean>) {

        arrivedSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer)
    }
}