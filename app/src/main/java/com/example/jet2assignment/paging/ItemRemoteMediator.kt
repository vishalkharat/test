package com.example.jet2assignment.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jet2assignment.data.db.AppDatabase
import com.example.jet2assignment.data.model.Item
import com.example.jet2assignment.data.model.RemoteKeys
import com.example.jet2assignment.data.remote.HomeItemService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(
	private val database: AppDatabase,
	private val homeItemService: HomeItemService
):
	RemoteMediator<Int, Item>() {

	override suspend fun load(loadType: LoadType, state: PagingState<Int, Item>):
			MediatorResult {
		return try {
			val page = when(loadType){
				LoadType.REFRESH -> {
					val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
					remoteKeys?.next?.minus(1)?:1
				}
				LoadType.PREPEND -> {
					val remoteKeys = getRemoteKeyForFirstItem(state)

					remoteKeys?.prev ?: return MediatorResult.Success(endOfPaginationReached = false)

					remoteKeys.prev
				}
				LoadType.APPEND -> {
					val remoteKeys = getRemoteKeyForLastItem(state)

					remoteKeys?.next?:return MediatorResult.Success(endOfPaginationReached = true)

					remoteKeys.next
				}
			}

			val response = homeItemService.getAllItems(page)

			database.withTransaction{
				if(loadType == LoadType.REFRESH){
					database.itemDao().clearItems()
					database.remoteKeysDao().clearKeys()
				}
//				val next = response.body()?.info?.next
//				val nextPage = next?.split("page=")?.get(1)?.toIntOrNull()
//				val prev:String? = response.body()?.info?.prev
//				val prevPage = prev?.split("page=")?.get(1)?.toIntOrNull()
//
				val keys = response.body()?.map {
					RemoteKeys(id = Integer.valueOf(it.id),next = 1,prev = 1)
				}
				response.body()?.let { database.itemDao().insertAllItems(it) }
				keys?.let { database.remoteKeysDao().insertAll(it) }
			}

			MediatorResult.Success(endOfPaginationReached = true)
		}catch (e:IOException){
			MediatorResult.Error(e)
		}catch (e:HttpException){
			MediatorResult.Error(e)
		}
	}

	private suspend fun getRemoteKeyForLastItem(state:PagingState<Int,Item>):RemoteKeys?{
		return state.pages.lastOrNull(){ it.data.isNotEmpty()}?.data?.lastOrNull()
			?.let {item ->
				database.remoteKeysDao().remoteKeysId(Integer.valueOf(item.id))
			}
	}

	private suspend fun getRemoteKeyForFirstItem(state:PagingState<Int,Item>):RemoteKeys?{
		return state.pages.firstOrNull(){it.data.isNotEmpty()}?.data?.firstOrNull()
			?.let { item ->
				database.remoteKeysDao().remoteKeysId(Integer.valueOf(item.id))
			}
	}

	private suspend fun getRemoteKeyClosestToCurrentPosition(
		state:PagingState<Int,Item>):RemoteKeys?{
		return state.anchorPosition?.let {position ->
			state.closestItemToPosition(position)?.id?.let {id ->
				database.remoteKeysDao().remoteKeysId(Integer.valueOf(id))
			}
		}
	}
}