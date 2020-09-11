package com.example.jet2assignment.data.repository

import com.example.jet2assignment.data.remote.ItemRemoteDataSource
import javax.inject.Inject

class ItemRepository @Inject constructor(
	private val itemRemoteDataSource: ItemRemoteDataSource

): Repository {
	
	fun getRemoteAndLocalFlow() = itemRemoteDataSource.getRemoteAndLocalFlow()
}
