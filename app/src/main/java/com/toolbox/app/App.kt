package com.toolbox.app

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mIntance = this
    }

    companion object {

        private lateinit var mIntance: App

        fun getApp(): App = mIntance

        fun getAppContext(): Context = mIntance

    }

}