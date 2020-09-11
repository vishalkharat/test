package com.example.jet2assignment.di

import com.example.jet2assignment.data.db.AppDatabase
import com.example.jet2assignment.data.remote.HomeItemService
import com.example.jet2assignment.data.remote.ItemRemoteDataSource
import com.example.jet2assignment.data.repository.ItemRepository
import com.example.jet2assignment.paging.ItemPageSource
import com.example.jet2assignment.paging.ItemRemoteMediator
import com.example.jet2assignment.view.activity.ui.home.HomePageAdapter
import com.example.jet2assignment.view.activity.ui.home.ItemComparator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {

	@Provides
	fun provideHomeItemService(retrofit: Retrofit): HomeItemService =
		retrofit.create(HomeItemService::class.java)
	
	@Provides
	fun provideItemRemoteMediator(appDatabase: AppDatabase, homeItemService:HomeItemService) =
		ItemRemoteMediator(appDatabase,homeItemService)

	@Provides
	fun provideRemoteItemDataSource(appDatabase: AppDatabase,itemRemoteMediator: ItemRemoteMediator) =
		ItemRemoteDataSource(appDatabase,itemRemoteMediator)

	@Provides
	fun provideItemRepository(itemRemoteDataSource: ItemRemoteDataSource) =
		ItemRepository(itemRemoteDataSource)
	
	@Provides
	fun provideItemPageSource(homeItemService: HomeItemService) =
		ItemPageSource(homeItemService)
	
	@Provides
	fun provideHomePageAdapter() =
		HomePageAdapter(ItemComparator)
}