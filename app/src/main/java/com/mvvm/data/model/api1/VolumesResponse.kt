package com.mvvm.data.model.api1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VolumesResponse(
    @SerializedName("kind") @Expose
    private var kind: String? = null,

    @SerializedName("items")
    @Expose
    var items: List<Volume>? = null,

    @SerializedName("totalItems")
    @Expose
    var totalItems: Int = 0
)
