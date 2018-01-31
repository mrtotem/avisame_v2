package com.mrtotem.avisame.models.responses

import com.mrtotem.avisame.models.Message

/**
 * Created by Toto on 31/1/2018.
 */
class AlertsResponse {

    private var alerts: List<Message>

    fun getAlerts(): List<Message> {
        return alerts
    }

    constructor (arrivals: List<Message>) {
        this.alerts = arrivals
    }
}