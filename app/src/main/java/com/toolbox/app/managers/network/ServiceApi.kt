package com.toolbox.app.managers.network

import com.toolbox.app.managers.preference.PreferencesManager
import com.toolbox.models.auth.Auth
import com.toolbox.models.auth.ResponseAuth
import com.toolbox.models.carousels.ResponseCarousels
import retrofit2.Call
import com.toolbox.utils.Constants
import retrofit2.http.*

interface ServiceApi {

    @Headers(Constants.HEADER_ACCEPT, Constants.HEADER_CONTENT_TYPE)
    @POST("v1/mobile/auth")
    fun auth(
        @Body auth: Auth
    ): Call<ResponseAuth>

    @Headers(Constants.HEADER_ACCEPT, Constants.HEADER_CONTENT_TYPE)
    @GET("v1/mobile/data")
    fun getCarousels(
        @Header("authorization") token: String
    ): Call<MutableList<ResponseCarousels>>

}