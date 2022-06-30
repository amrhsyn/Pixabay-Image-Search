package me.ahch.core.utils

fun String.tagsToArray(): List<String> {
    return this.split(",")
}