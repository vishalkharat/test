package com.example.jet2assignment.data.model

import androidx.room.*
import com.example.jet2assignment.data.db.DataConverter

@Entity(tableName = "items")
data class Item(
	val createdAt: String,
	val content: String,
	@PrimaryKey
	val id: String,
	val comments: Int,
	val likes: Int
//	@TypeConverters(DataConverter::class)
//	val media: Media1?
)