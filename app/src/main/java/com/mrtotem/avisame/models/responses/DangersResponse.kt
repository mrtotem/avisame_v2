package com.mrtotem.avisame.models.responses

import com.mrtotem.avisame.models.Message

/**
 * Created by Toto on 31/1/2018.
 */
class DangersResponse {

    private var dangers: List<Message>

    fun getDangets(): List<Message> {
        return dangers
    }

    constructor (dangers: List<Message>) {
        this.dangers = dangers
    }
}