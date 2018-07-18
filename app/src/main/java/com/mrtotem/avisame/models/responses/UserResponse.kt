package com.mrtotem.avisame.models.responses

import com.mrtotem.avisame.models.User

/**
 * Created by Toto on 31/1/2018.
 */
class UserResponse {

    private var user: User

    fun getUser(): User {
        return user
    }

    constructor (user: User) {
        this.user = user
    }
}