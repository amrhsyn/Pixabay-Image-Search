package me.ahch.core.model

data class Hit (
    val id: Int,
    val user: String,
    val largeImageURL: String,
    val webFormatURL: String,
    val tags: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
)