package com.example.jet2assignment.di

import android.content.Context
import com.example.jet2assignment.data.db.AppDatabase
import com.example.jet2assignment.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
	
	@Provides
	@Singleton
	fun provideRetrofit() = Util.provideRetrofit()
	
	@Provides
	@Singleton
	fun provideDb(@ApplicationContext context: Context) =
		AppDatabase.getDatabase(context)
}