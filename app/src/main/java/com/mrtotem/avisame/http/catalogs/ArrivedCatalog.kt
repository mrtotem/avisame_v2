package com.mrtotem.avisame.http.catalogs

import com.mrtotem.avisame.models.Message
import com.mrtotem.avisame.models.responses.ArrivalsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Octavio on 01/02/2018.
 */
class ArrivedCatalog : Callback<ArrivalsResponse> {

    var arrivedMessages: ArrayList<Message>? = arrayListOf()

    override fun onFailure(call: Call<ArrivalsResponse>?, t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call<ArrivalsResponse>?, response: Response<ArrivalsResponse>?) {
        arrivedMessages = response?.body()?.getArrivals()
    }
}