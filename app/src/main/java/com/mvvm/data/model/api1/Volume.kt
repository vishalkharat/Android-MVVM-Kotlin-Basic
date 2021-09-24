package com.mvvm.data.model.api1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Volume(
    @SerializedName("kind") @Expose
    private var kind: String? = null,

    @SerializedName("id")
    @Expose
    private val id: String? = null,

    @SerializedName("etag")
    @Expose
    private val etag: String? = null,

    @SerializedName("selfLink")
    @Expose
    private val selfLink: String? = null,

    @SerializedName("volumeInfo")
    @Expose
    var volumeInfo: VolumeInfo? = null
)
