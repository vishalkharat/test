package com.example.jet2assignment.data.model


data class Article(
	val createdAt: String,
	val content: String,
	val id: String,
	val comments: Int,
	val likes: Int,
	val media: ArrayList<Media>?
)