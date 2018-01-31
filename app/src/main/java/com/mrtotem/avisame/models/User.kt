package com.mrtotem.avisame.models

/**
 * Created by Toto on 31/1/2018.
 */
class User {

    private var _id: String? = ""
    private var token: String = ""
    private var pushToken: String = ""
    private var email: String? = null
    private var password: String = ""
    private var firstName: String = ""
    private var lastName: String = ""
    private var dni: String = ""

    private var friends: List<String>? = null

    constructor(id: String, token: String, pushToken: String, email: String, password: String, firstName: String, lastName: String, dni: String) {
        this._id = id
        this.token = token
        this.pushToken = pushToken
        this.email = email
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.dni = dni
    }

    constructor(id: String, email: String) {
        this._id = id
        this.email = email
    }

    fun getPushToken(): String {
        return pushToken
    }

    fun getToken(): String {
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

    fun getDni(): String {
        return dni
    }

    fun getFriends(): List<String>? {
        return friends
    }

    fun setFriends(friends: List<String>) {
        this.friends = friends
    }
}