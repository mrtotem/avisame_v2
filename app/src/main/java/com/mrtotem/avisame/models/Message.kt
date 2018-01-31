package com.mrtotem.avisame.models

import java.io.Serializable


/**
 * Created by Toto on 31/1/2018.
 */
class Message : Serializable {

    private var _id: String = ""
    private var userId: String = ""
    private var name: String = ""
    private var message: String = ""
    private var date: String = ""
    private var lat: String = ""
    private var long: String = ""
    private var type: String = ""

    constructor(_id: String, userId: String, name: String, message: String, date: String, lat: String, long: String, type: String) {
        this._id = _id
        this.userId = userId
        this.name = name
        this.message = message
        this.date = date
        this.lat = lat
        this.long = long
        this.type = type
    }

    fun getId(): String {
        return _id
    }

    fun getUserId(): String {
        return userId
    }

    fun getName(): String {
        return name
    }

    fun getMessage(): String {
        return message
    }

    fun getDate(): String {
        return date
    }

    fun getLatitude(): String {
        return lat
    }

    fun getLongitude(): String {
        return long
    }

    fun getType(): String {
        return type
    }
}