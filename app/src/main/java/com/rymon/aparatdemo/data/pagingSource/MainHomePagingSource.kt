package com.rymon.aparatdemo.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rymon.aparatdemo.api.AparatApi
import com.rymon.aparatdemo.data.main.Data
import com.rymon.aparatdemo.data.main.addVideoAttributesToData
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_OFFSET = ""
private const val PER_PAGE_COUNT = 60



 class SearchVideoPagingSource2(
    private val aparatApi: AparatApi,
) : PagingSource<String, Data>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Data> {
        val position = params.key ?: STARTING_PAGE_OFFSET
        return try {

        val response = aparatApi.searchVideos2(position)
            val metaData = response.mainData
            val includedVideo = response.included
            val nextLinks = response.links
            val nextLinkPart = nextLinks?.getNextLinkPart() ?:""

            metaData.forEach{
                addVideoAttributesToData(it,includedVideo)
            }

                LoadResult.Page(
                    data = metaData,
                    prevKey = null,
                    nextKey = nextLinkPart
                )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

     override fun getRefreshKey(state: PagingState<String, Data>): String? {
         val a = state
         TODO("Not yet implemented")
     }

     override val keyReuseSupported: Boolean
         get() = true
 }