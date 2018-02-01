package com.mrtotem.avisame.models.responses

import com.mrtotem.avisame.models.Message

/**
 * Created by Toto on 31/1/2018.
 */
class ArrivalsResponse {

    private var arrivals: ArrayList<Message>

    fun getArrivals(): ArrayList<Message> {
        return arrivals
    }

    constructor (arrivals: ArrayList<Message>) {
        this.arrivals = arrivals
    }
}