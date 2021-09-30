package com.toolbox.ui.carousels.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toolbox.app.managers.preference.PreferencesManager
import com.toolbox.models.auth.ResponseAuth
import com.toolbox.models.carousels.ResponseCarousels
import com.toolbox.utils.Constants
import com.toolbox.utils.Utils

class CarouselsVM(
    private val repository: CarouselsRepository
) : ViewModel() {

    private val aux = mutableListOf<ResponseCarousels>()
    val carousels = MutableLiveData<MutableList<ResponseCarousels>>()

    fun auth() {
        repository.auth(object : OnResponse<ResponseAuth> {
            override fun onSuccess(response: ResponseAuth?) {
                PreferencesManager.setString(Constants.PREFERENCES.TYPE, response!!.type!!)
                PreferencesManager.setString(Constants.PREFERENCES.TOKEN, response!!.token!!)
                getCarousels()
            }

            override fun onFailed(error: String) {
                Utils.showShortToast(error)
            }

        })
    }

    fun getCarousels() {
        repository.getCarousels(object : OnResponse<MutableList<ResponseCarousels>> {
            override fun onSuccess(response: MutableList<ResponseCarousels>?) {
                if (response != null) {
                    setData(response)
                }
            }

            override fun onFailed(error: String) {
                auth()
            }

        })
    }


    fun setData(list: MutableList<ResponseCarousels>) {
        aux.addAll(list)
        carousels.value = aux
    }

    interface OnResponse<T> {
        fun onSuccess(response: T?)
        fun onFailed(error: String)
    }

}