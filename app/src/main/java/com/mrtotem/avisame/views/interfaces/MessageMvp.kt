package com.mrtotem.avisame.views.interfaces

import com.mrtotem.avisame.models.Message

/**
 * Created by Octavio on 31/01/2018.
 */
interface MessageMvp {

    fun refreshMessages(messages: ArrayList<Message>)
}