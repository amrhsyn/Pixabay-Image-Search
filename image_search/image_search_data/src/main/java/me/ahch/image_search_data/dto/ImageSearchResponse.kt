package me.ahch.image_search_data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ImageSearchResponse(
    @SerializedName("hits")
    val hits: List<HitDto?>? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("totalHits")
    val totalHits: Int? = null
)