package me.ahch.image_search_data.dto

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.ahch.image_search_data.util.Constants

@Keep
@Entity(tableName = Constants.IMAGE_TABLE)
data class HitDto(
    @SerializedName("id")
    @PrimaryKey()
    val id: Int,
    @SerializedName("collections")
    @Ignore
    val collections: Int,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("imageHeight")
    @Ignore
    val imageHeight: Int,
    @SerializedName("imageSize")
    @Ignore
    val imageSize: Int,
    @SerializedName("imageWidth")
    @Ignore
    val imageWidth: Int,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("pageURL")
    @Ignore
    val pageURL: String,
    @SerializedName("previewHeight")
    @Ignore
    val previewHeight: Int,
    @SerializedName("previewURL")
    @Ignore
    val previewURL: String,
    @SerializedName("previewWidth")
    @Ignore
    val previewWidth: Int,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("user_id")
    @Ignore
    val userId: Int,
    @SerializedName("userImageURL")
    @Ignore
    val userImageURL: String,
    @SerializedName("views")
    @Ignore
    val views: Int,
    @SerializedName("webformatHeight")
    @Ignore
    val webormatHeight: Int,
    @SerializedName("webformatURL")
    val webFormatURL: String,
    @SerializedName("webformatWidth")
    @Ignore
    val webformatWidth: Int
)