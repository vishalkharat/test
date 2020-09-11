package com.example.jet2assignment.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.jet2assignment.data.db.AppDatabase
import com.example.jet2assignment.paging.ItemRemoteMediator
import javax.inject.Inject

class ItemRemoteDataSource @Inject constructor(
	private val appDatabase: AppDatabase,
	private val itemRemoteMediator: ItemRemoteMediator
) : BaseDataSource {
	
	fun getRemoteAndLocalFlow() = Pager(
		config =  PagingConfig(5),
		remoteMediator = itemRemoteMediator
	){
		appDatabase.itemDao().getAllItems()
	}.flow
	
}