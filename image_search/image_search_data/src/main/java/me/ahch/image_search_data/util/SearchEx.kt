package me.ahch.image_search_data.util

fun String.toPixabayQuery():String{
   return this.replace(" ","+")
}