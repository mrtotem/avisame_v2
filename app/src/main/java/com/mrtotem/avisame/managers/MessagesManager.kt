package com.mrtotem.avisame.managers

/**
 * Created by Octavio on 26/01/2018.
 */

class MessagesManager private constructor() {

    init {
        /*
        *  every time init is called increment instance count
        *  just in case somehow we break singleton rule, this will be
        *  called more than once and myInstancesCount > 1 == true
        */
        ++myInstancesCount
    }


    companion object {
        //Debuggable field to check instance count
        var myInstancesCount = 0
        private val mInstance: MessagesManager = MessagesManager()

        @Synchronized
        fun getInstance(): MessagesManager {
            return mInstance
        }
    }
}