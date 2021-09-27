package com.mvvm.ui.book

import Json4Kotlin_Base
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.data.model.api1.VolumesResponse
import com.mvvm.data.service.BookRepository

class BookSearchViewModel(application: Application) : AndroidViewModel(application) {
    private var bookRepository: BookRepository? = null
    private var volumesResponseLiveData: LiveData<VolumesResponse?>? = null
    private var volumesDetailsResponseLiveData: LiveData<Json4Kotlin_Base?>? = null

    fun init() {
        bookRepository = BookRepository()
        volumesResponseLiveData = bookRepository!!.getVolumesResponseLiveData()
        volumesDetailsResponseLiveData = bookRepository!!.getVolumeDetailsResponseLiveData()
    }

    fun searchVolumes(keyword: String?, author: String?) {
        bookRepository?.searchVolumes(keyword, author)
    }

    fun getVolumeDetails(keyword: String?, author: String?) {
        bookRepository?.getVolumeDetails(keyword, author)
    }

    fun getVolumesResponseLiveData(): LiveData<VolumesResponse?>? {
        return volumesResponseLiveData
    }

    fun getVolumeDetailsResponseLiveData(): LiveData<Json4Kotlin_Base?>? {
        return volumesDetailsResponseLiveData
    }
}