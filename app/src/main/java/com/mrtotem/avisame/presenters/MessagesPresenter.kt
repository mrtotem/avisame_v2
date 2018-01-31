package com.mrtotem.avisame.presenters

import android.util.Log
import com.mrtotem.avisame.managers.MessagesManager
import com.mrtotem.avisame.views.interfaces.BaseMvp
import com.mrtotem.avisame.views.interfaces.MessageMvp
import io.reactivex.functions.Consumer

/**
 * Created by Octavio on 29/01/2018.
 */
class MessagesPresenter : BasePresenter<BaseMvp>() {

    private val tag: String = "MessagesPresenter"

    lateinit var mListener: MessageMvp

    fun suscribeToMessagesSubject() {

        MessagesManager.getInstance().suscribeToMessageSubject(Consumer {
            if (it.error != null) {
                Log.e(tag, "Messages error")
            } else {
                Log.d(tag, "Messages received")
                mListener.refreshMessages(MessagesManager.getInstance().messages)
            }
        })
    }

    fun registerMessageListener(listener: MessageMvp) {
        mListener = listener
    }
}