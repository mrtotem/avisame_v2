package com.mrtotem.avisame.models

import java.io.Serializable

/**
 * Created by Toto on 31/1/2018.
 */
class Location : Serializable {

    private var latitude: Double
    private var longitude: Double

    constructor(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }
}