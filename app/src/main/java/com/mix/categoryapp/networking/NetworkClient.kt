package com.mix.categoryapp.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mix.categoryapp.model.CategoryOuterEntity
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


interface NetworkClient {

    @Headers("user-key: dab40b37d6a30e8a5b1f0a67344c545b")
    @GET("categories")
    fun getCategoriesList(): Deferred<CategoryOuterEntity>

    companion object{
        //creating a Network Interceptor to add api_key in all the request as authInterceptor
        private val interceptor = Interceptor { chain: Interceptor.Chain ->
            val url = chain.request().url.newBuilder().addQueryParameter(
                "user-key", "c750173e8cf7e5fdc2c331cf897ee8c3"
            ).build()
            val request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }
        // we are creating a networking client using OkHttp and add our authInterceptor.
        private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .client(apiClient)
                .baseUrl("https://developers.zomato.com/api/v2.1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        val networkInstance: NetworkClient = getRetrofit().create(NetworkClient::class.java)
    }

}