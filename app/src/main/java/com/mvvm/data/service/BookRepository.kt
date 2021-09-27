package com.mvvm.data.service

import Json4Kotlin_Base
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.data.model.api1.BookSearchService
import com.mvvm.data.model.api1.VolumesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository {


    private var bookSearchService: BookSearchService? = null
    private var volumesResponseLiveData: MutableLiveData<VolumesResponse?>? = null
    private var volumesDetailsResponseLiveData: MutableLiveData<Json4Kotlin_Base?>? = null

    init {
        volumesResponseLiveData = MutableLiveData<VolumesResponse?>()
        volumesDetailsResponseLiveData = MutableLiveData<Json4Kotlin_Base?>()
        bookSearchService = RetrofitService.createService(BookSearchService::class.java)
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//        bookSearchService = Retrofit.Builder()
//            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(BookSearchService::class.java)
    }

    fun searchVolumes(keyword: String?, author: String?) {
        bookSearchService?.searchVolumes(keyword, author)
            ?.enqueue(object : Callback<VolumesResponse?> {
                override fun onResponse(
                    call: Call<VolumesResponse?>,
                    response: Response<VolumesResponse?>
                ) {
                    if (response.body() != null) {
                        volumesResponseLiveData!!.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<VolumesResponse?>, t: Throwable) {
                    volumesResponseLiveData!!.postValue(null)
                }
            })
    }

    fun getVolumeDetails(keyword: String?, author: String?) {
        bookSearchService?.getVolumeDetails()
            ?.enqueue(object : Callback<Json4Kotlin_Base?> {
                override fun onResponse(
                    call: Call<Json4Kotlin_Base?>,
                    response: Response<Json4Kotlin_Base?>
                ) {
                    if (response.body() != null) {
                        volumesDetailsResponseLiveData!!.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Json4Kotlin_Base?>, t: Throwable) {
                    volumesDetailsResponseLiveData!!.postValue(null)
                }
            })
    }

    fun getVolumesResponseLiveData(): LiveData<VolumesResponse?>? {
        return volumesResponseLiveData
    }

    fun getVolumeDetailsResponseLiveData(): LiveData<Json4Kotlin_Base?>? {
        return volumesDetailsResponseLiveData
    }
}