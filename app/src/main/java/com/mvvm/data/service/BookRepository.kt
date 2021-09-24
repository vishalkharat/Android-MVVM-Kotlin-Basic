package com.mvvm.data.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.data.model.api1.BookSearchService
import com.mvvm.data.model.api1.VolumesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository {
    private val BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/"

    private var bookSearchService: BookSearchService? = null
    private var volumesResponseLiveData: MutableLiveData<VolumesResponse?>? = null

    init {
        volumesResponseLiveData = MutableLiveData<VolumesResponse?>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        bookSearchService = Retrofit.Builder()
            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookSearchService::class.java)
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

    fun getVolumesResponseLiveData(): LiveData<VolumesResponse?>? {
        return volumesResponseLiveData
    }
}