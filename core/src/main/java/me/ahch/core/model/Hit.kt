package me.ahch.core.model

data class Hit (
    val id: Int? = null,
    val user: String? = null,
    val largeImageURL: String? = null,
    val previewURL: String? = null,
    val tags: String? = null,
    val likes: Int? = null,
    val downloads: Int? = null,
    val comments: Int? = null,
)