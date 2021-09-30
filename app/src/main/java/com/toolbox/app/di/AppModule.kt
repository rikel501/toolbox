package com.toolbox.app.di

import dagger.Module
import dagger.Provides
import com.toolbox.app.managers.network.NetworkManager
import com.toolbox.app.managers.network.ServiceApi
import com.toolbox.ui.carousels.mvvm.CarouselsRepository
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(): ServiceApi = NetworkManager.serviceApi

    @Provides
    fun provideRepository() = CarouselsRepository()

}