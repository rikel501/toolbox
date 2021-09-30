package com.toolbox.ui.carousels.mvvm

import com.toolbox.app.di.DaggerAppComponent
import com.toolbox.app.managers.network.ServiceApi
import com.toolbox.app.managers.preference.PreferencesManager
import com.toolbox.models.auth.Auth
import com.toolbox.models.auth.ResponseAuth
import com.toolbox.models.carousels.ResponseCarousels
import com.toolbox.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CarouselsRepository() {

    @Inject
    lateinit var api: ServiceApi

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun auth(onResponse: CarouselsVM.OnResponse<ResponseAuth>) {
        val item = Auth("ToolboxMobileTest")
        api.auth(item).enqueue(object : Callback<ResponseAuth> {
            override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
                when (response.code()) {
                    200 -> {
                        onResponse.onSuccess(response.body())
                    }
                    401 -> {
                        onResponse.onFailed("Unauthorized")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                onResponse.onFailed("error: Unauthorized")
            }

        })
    }

    fun getCarousels(onResponse: CarouselsVM.OnResponse<MutableList<ResponseCarousels>>) {
        api.getCarousels(
            PreferencesManager.getString(Constants.PREFERENCES.TYPE, "") + " " +
                    PreferencesManager.getString(Constants.PREFERENCES.TOKEN, "")!!
        ).enqueue(object : Callback<MutableList<ResponseCarousels>> {
            override fun onResponse(
                call: Call<MutableList<ResponseCarousels>>,
                response: Response<MutableList<ResponseCarousels>>
            ) {
                when (response.code()) {
                    200 -> {
                        onResponse.onSuccess(response.body())
                    }
                    401 -> {
                        onResponse.onFailed("Unauthorized")
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<ResponseCarousels>>, t: Throwable) {
                onResponse.onFailed("error: Unauthorized")
            }

        })
    }

}