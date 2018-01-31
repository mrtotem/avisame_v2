package com.mrtotem.avisame.models.responses

import com.mrtotem.avisame.models.Message

/**
 * Created by Toto on 31/1/2018.
 */
class ArrivalsResponse {

    private var arrivals: List<Message>

    fun getArrivals(): List<Message> {
        return arrivals
    }

    constructor (arrivals: List<Message>) {
        this.arrivals = arrivals
    }
}