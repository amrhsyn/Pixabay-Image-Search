package me.ahch.image_search_data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ahch.image_search_data.dto.ImagesRemoteKeys

interface ImagesRemoteKeysDao {

    @Query("SELECT * FROM images_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): ImagesRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<ImagesRemoteKeys>)

    @Query("DELETE FROM images_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}