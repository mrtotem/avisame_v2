package com.mrtotem.avisame.models.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by Toto on 31/1/2018.
 */
class RegisterResponse {

    @SerializedName("_id")
    var id: String
    var token: String

    constructor (userId: String, token: String) {
        this.id = userId
        this.token = token
    }
}