package me.ahch.image_search_data.dto

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.ahch.image_search_data.util.Constants.REMOTE_KEYS_TABLE


@Entity(tableName = REMOTE_KEYS_TABLE)
data class ImagesRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)