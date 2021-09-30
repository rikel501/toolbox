package com.toolbox.ui.carousels.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CarouselsVMF(
    private val repository: CarouselsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarouselsVM::class.java)) {
            return CarouselsVM(repository) as T
        } else {
            throw IllegalArgumentException("Unknown View Model class")
        }
    }

}