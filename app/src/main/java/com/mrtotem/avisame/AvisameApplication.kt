package com.mrtotem.avisame

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco


/**
 * Created by Octavio on 26/01/2018.
 */

class AvisameApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }
}