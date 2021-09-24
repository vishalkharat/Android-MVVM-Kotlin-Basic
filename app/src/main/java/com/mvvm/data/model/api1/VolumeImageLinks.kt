package com.mvvm.data.model.api1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VolumeImageLinks(
    @SerializedName("smallThumbnail") @Expose
    var smallThumbnail: String? = null,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String? = null
)
