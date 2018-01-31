package com.mrtotem.avisame.http

import com.mrtotem.avisame.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Octavio on 31/01/2018.
 */
class AvisameApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}