package com.mrtotem.avisame.models

import com.google.gson.annotations.Expose

/**
 * Created by Toto on 31/1/2018.
 */
class User {

    private var _id: String? = ""
    private var token: String? = ""
    private var pushToken: String = ""
    private var email: String? = null
    private var password: String = ""
    private var firstName: String = ""
    private var lastName: String = ""

    constructor(id: String, token: String, pushToken: String, email: String, password: String, firstName: String, lastName: String, dni: String) {
        this._id = id
        this.token = token
        this.pushToken = pushToken
        this.email = email
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
    }

    constructor(id: String?, token: String?) {
        this._id = id
        this.token = token
    }

    fun getPushToken(): String {
        return pushToken
    }

    fun getToken(): String? {
        return token
    }

    fun getId(): String? {
        return _id
    }

    fun getEmail(): String? {
        return email
    }

    fun getPassword(): String {
        return password
    }

    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }
}