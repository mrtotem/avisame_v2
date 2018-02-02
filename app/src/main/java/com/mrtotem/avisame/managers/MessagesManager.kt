package com.mrtotem.avisame.managers

import com.mrtotem.avisame.http.AvisameApiClient
import com.mrtotem.avisame.http.catalogs.ArrivedCatalog
import com.mrtotem.avisame.http.interfaces.MessagesService
import com.mrtotem.avisame.models.Message
import com.mrtotem.avisame.models.responses.ArrivalsResponse
import com.mrtotem.avisame.models.responses.SubjectItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call

/**
 * Created by Octavio on 26/01/2018.
 */

class MessagesManager private constructor() {

    private val messagesGetterSubject: PublishSubject<SubjectItem<Boolean>> = PublishSubject.create()
    private val arrivedCatalog: ArrivedCatalog = ArrivedCatalog()

    private var messagesService: MessagesService? = null
    var messages: ArrayList<Message> = arrayListOf()

    init {
        /*
        *  every time init is called increment instance count
        *  just in case somehow we break singleton rule, this will be
        *  called more than once and myInstancesCount > 1 == true
        */
        ++myInstancesCount
    }


    companion object {
        //Debuggable field to check instance count
        var myInstancesCount = 0
        private val mInstance: MessagesManager = MessagesManager()

        @Synchronized
        fun getInstance(): MessagesManager {
            return mInstance
        }
    }

    fun init() {
        messagesService = AvisameApiClient().getClient()?.create<MessagesService>(MessagesService::class.java)
    }

    fun suscribeToMessageSubject(consumer: Consumer<SubjectItem<Boolean>>) {

        messagesGetterSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer)
    }

    fun fetchLastestMessages() {

        val arrivalsCall: Call<ArrivalsResponse>? = messagesService?.getArrivalMessages("", "", "")
        arrivedCatalog.subscribeToArrivalsSubject(Consumer {
            if (it) {
                messagesGetterSubject.onNext(SubjectItem(null, true))
            }
        })
        arrivalsCall?.enqueue(arrivedCatalog)
    }
}