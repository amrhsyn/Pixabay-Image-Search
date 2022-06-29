package me.ahch.image_search_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.ahch.image_search_data.dto.HitDto
import me.ahch.image_search_data.dto.ImagesRemoteKeys
import me.ahch.image_search_data.local.dao.ImagesDao
import me.ahch.image_search_data.local.dao.ImagesRemoteKeysDao

@Database(entities = [HitDto::class, ImagesRemoteKeys::class], version = 1)
abstract class PixabayDatabase : RoomDatabase(){
    abstract fun imagesDao(): ImagesDao
    abstract fun imagesRemoteKeysDao(): ImagesRemoteKeysDao
}