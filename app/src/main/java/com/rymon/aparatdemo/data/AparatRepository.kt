package com.rymon.aparatdemo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rymon.aparatdemo.api.AparatApi
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import com.rymon.aparatdemo.data.pagingSource.HomeVideoPagingSource
import com.rymon.aparatdemo.data.pagingSource.SearchVideoPagingSource
import com.rymon.aparatdemo.data.pagingSource.SearchVideoPagingSource2

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AparatRepository @Inject constructor(private val aparatApi: AparatApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchVideoPagingSource(aparatApi, query) }
        ).liveData

    fun getMainHomeData() =
        Pager(
            config = PagingConfig(
                pageSize = 4,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchVideoPagingSource2(aparatApi) }
        ).liveData

    fun getHomeResult() =
        Pager(
            config = PagingConfig(
                pageSize = 6,
                maxSize = 200,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { HomeVideoPagingSource(aparatApi) }
        ).liveData

      suspend fun getHomeResultWithoutPaging()= aparatApi.getHomeVideo()



}