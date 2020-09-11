package com.example.jet2assignment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jet2assignment.data.model.Item
import com.example.jet2assignment.data.model.Media
import com.example.jet2assignment.data.model.RemoteKeys

@Database(entities = [Item::class, RemoteKeys::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
	
	abstract fun itemDao(): ItemDao
	
	abstract fun remoteKeysDao(): RemoteKeysDao
	
	companion object{
		@Volatile private var instance:AppDatabase? = null
		fun getDatabase(context: Context):AppDatabase =
			instance?: synchronized(this){
				instance?: buildDatabase(context).apply { instance = this }
			}
		
		private fun buildDatabase(context: Context) =
			Room.databaseBuilder(context,AppDatabase::class.java,"items.db")
				.fallbackToDestructiveMigration()
				.build()
	}
}