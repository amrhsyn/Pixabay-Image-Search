package me.ahch.image_search_data.mapper

import me.ahch.core.model.Hit
import me.ahch.image_search_data.dto.HitDto

fun HitDto.toHit(): Hit = Hit(
    id = this.id,
    user = this.user,
    largeImageURL = this.largeImageURL,
    webFormatURL = this.webFormatURL,
    tags = this.tags,
    likes = this.likes,
    downloads = this.downloads,
    comments = this.comments,
)
