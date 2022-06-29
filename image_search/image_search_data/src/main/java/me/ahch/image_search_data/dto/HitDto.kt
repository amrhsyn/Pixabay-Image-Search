package me.ahch.image_search_data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HitDto(
    @SerializedName("collections")
    val collections: Int? = null,
    @SerializedName("comments")
    val comments: Int? = null,
    @SerializedName("downloads")
    val downloads: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imageHeight")
    val imageHeight: Int? = null,
    @SerializedName("imageSize")
    val imageSize: Int? = null,
    @SerializedName("imageWidth")
    val imageWidth: Int? = null,
    @SerializedName("largeImageURL")
    val largeImageURL: String? = null,
    @SerializedName("likes")
    val likes: Int? = null,
    @SerializedName("pageURL")
    val pageURL: String? = null,
    @SerializedName("previewHeight")
    val previewHeight: Int? = null,
    @SerializedName("previewURL")
    val previewURL: String? = null,
    @SerializedName("previewWidth")
    val previewWidth: Int? = null,
    @SerializedName("tags")
    val tags: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("user")
    val user: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null,
    @SerializedName("userImageURL")
    val userImageURL: String? = null,
    @SerializedName("views")
    val views: Int? = null,
    @SerializedName("webformatHeight")
    val webformatHeight: Int? = null,
    @SerializedName("webformatURL")
    val webformatURL: String? = null,
    @SerializedName("webformatWidth")
    val webformatWidth: Int? = null
)