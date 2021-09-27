package com.mvvm.data.model.api1

import Json4Kotlin_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchService {
    @GET("/books/v1/volumes")
    fun searchVolumes(
        @Query("q") query: String?,
        @Query("inauthor") author: String?
    ): Call<VolumesResponse?>?


    @GET("/books/v1/volumes/ULP5AwAAQBAJ")
    fun getVolumeDetails(): Call<Json4Kotlin_Base?>?
}