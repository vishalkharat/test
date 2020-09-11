package com.example.jet2assignment.data.remote

import com.example.jet2assignment.data.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeItemService {

	@GET("api/v1/blogs")
	suspend fun getAllItems(@Query("page")page:Int):Response<List<Item>>
}