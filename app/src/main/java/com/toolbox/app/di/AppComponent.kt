package com.toolbox.app.di

import com.toolbox.ui.carousels.CarouselsFragment
import com.toolbox.ui.carousels.mvvm.CarouselsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(repository: CarouselsRepository)
    fun inject(view: CarouselsFragment)

}