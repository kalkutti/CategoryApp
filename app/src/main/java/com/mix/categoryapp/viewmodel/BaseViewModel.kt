package com.mix.categoryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mix.categoryapp.model.CategoryDetailEntity


class BaseViewModel : ViewModel() {

    val categRepository = CategoryRepo()
    val allCateg: MutableLiveData<MutableList<CategoryDetailEntity>> get() = categRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        categRepository.completableJob.cancel()
    }
}
