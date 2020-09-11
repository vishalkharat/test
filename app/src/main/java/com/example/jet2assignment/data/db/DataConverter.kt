package com.example.jet2assignment.data.db

import androidx.room.TypeConverter
import com.example.jet2assignment.data.model.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromCountryLangList(value: List<Media>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Media>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Media> {
        val gson = Gson()
        val type = object : TypeToken<List<Media>>() {}.type
        return gson.fromJson(value, type)
    }
}