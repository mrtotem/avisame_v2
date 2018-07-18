package com.mrtotem.avisame.http.interfaces

import com.mrtotem.avisame.models.responses.LoginResponse
import com.mrtotem.avisame.models.responses.RegisterResponse
import com.mrtotem.avisame.models.responses.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @POST("users/login")
    @FormUrlEncoded
    fun loginToUser(@Field("email") email: String,
                    @Field("password") password: String): Call<LoginResponse>

    @POST("users")
    @FormUrlEncoded
    fun registerAnUser(@Field("email") email: String,
                       @Field("password") password: String): Call<RegisterResponse>

    @GET("users/?method=_userId")
    fun getUser(@Header("Authorization") token: String?,
                @Query("_userId") userId: String?): Call<UserResponse>

}