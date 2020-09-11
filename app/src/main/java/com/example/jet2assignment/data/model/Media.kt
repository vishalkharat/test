package com.example.jet2assignment.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

//@Entity(
//	tableName = "media",
//	foreignKeys = [ForeignKey(entity = Item::class,
//	parentColumns = arrayOf("id"),
//	childColumns = arrayOf("id"),
//	onDelete = ForeignKey.CASCADE)]
//)
data class Media(
	val id: String,
//	val image: String,
	val blogId: Int,
	val createdAt: String,
	val image: String,
	val title: String,
	val url: String
	)