package com.mvvm.ui.book

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mvvm.data.model.api1.VolumesResponse
import com.mvvm.data.service.BookRepository

class BookSearchViewModel(application: Application) : AndroidViewModel(application) {
    private var bookRepository: BookRepository? = null
    private var volumesResponseLiveData: LiveData<VolumesResponse?>? = null

    fun init() {
        bookRepository = BookRepository()
        volumesResponseLiveData = bookRepository!!.getVolumesResponseLiveData()
    }

    fun searchVolumes(keyword: String?, author: String?) {
        bookRepository?.searchVolumes(keyword, author)
    }

    fun getVolumesResponseLiveData(): LiveData<VolumesResponse?>? {
        return volumesResponseLiveData
    }
}