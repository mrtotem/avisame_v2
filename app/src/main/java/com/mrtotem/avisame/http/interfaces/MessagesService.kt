package com.mrtotem.avisame.http.interfaces

import com.mrtotem.avisame.models.responses.AlertsResponse
import com.mrtotem.avisame.models.responses.ArrivalsResponse
import com.mrtotem.avisame.models.responses.DangersResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Octavio on 31/01/2018.
 */
interface MessagesService {

    @Headers("Bearer {header}")
    @GET("arrivals/{email}")
    fun getArrivalMessages(@Header("header") header: String, @Path("email") email: String, @Query("api_key") apiKey: String): Call<ArrivalsResponse>

    @Headers("Bearer {header}")
    @GET("alerts/{email}")
    fun getAlertMessages(@Header("header") header: String, @Path("email") email: String, @Query("api_key") apiKey: String): Call<AlertsResponse>

    @Headers("Bearer {header}")
    @GET("dangers/{email}")
    fun getDangerMessages(@Header("header") header: String, @Path("email") email: String, @Query("api_key") apiKey: String): Call<DangersResponse>
}