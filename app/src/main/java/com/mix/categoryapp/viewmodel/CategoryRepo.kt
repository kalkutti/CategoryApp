package com.mix.categoryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mix.categoryapp.model.CategoryDetailEntity
import com.mix.categoryapp.networking.NetworkClient
import kotlinx.coroutines.*
import retrofit2.HttpException

class CategoryRepo {

    private var categories = mutableListOf<CategoryDetailEntity>()
    private var mutableLiveData = MutableLiveData<MutableList<CategoryDetailEntity>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val mNetworkClient: NetworkClient by lazy {
        NetworkClient.networkInstance
    }

    fun getMutableLiveData(): MutableLiveData<MutableList<CategoryDetailEntity>> {
        coroutineScope.launch {
            val request = mNetworkClient.getCategoriesList()
            withContext(Dispatchers.Main) {
                try {

                    val response = request.await()
                    val mCategoryOuterEntity = response
                    if (mCategoryOuterEntity != null && mCategoryOuterEntity.categories != null) {
                        categories = mCategoryOuterEntity.categories as MutableList<CategoryDetailEntity>
                        mutableLiveData.value = categories
                    }

                } catch (e: HttpException) {
                    // Log exception //

                } catch (e: Throwable) {
                    // Log error //)
                }
            }
        }
        return mutableLiveData;
    }
}