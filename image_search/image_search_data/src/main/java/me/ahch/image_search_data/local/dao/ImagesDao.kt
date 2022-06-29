package me.ahch.image_search_data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ahch.image_search_data.dto.HitDto

@Dao
interface ImagesDao {

    @Query("SELECT * FROM pixabay_image_table")
    fun getAllImages(): PagingSource<Int, HitDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<HitDto>)

    @Query("DELETE FROM pixabay_image_table")
    suspend fun deleteAllImages()

}