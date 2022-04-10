package com.rymon.aparatdemo.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rymon.aparatdemo.api.AparatApi
import com.rymon.aparatdemo.data.models.Video
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_OFFSET = 0
private const val STARTING_PAGE_UNIQUE_ID = 0
private const val PER_PAGE_COUNT = 10



 class CategorySearchVideoPagingSource(
    private val aparatApi: AparatApi,
    private val query: Int
) : PagingSource<Int, Video>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Video> {
        val position = params.key ?: STARTING_PAGE_OFFSET
        val uniqueId = params.key ?: STARTING_PAGE_UNIQUE_ID
        return try {

            val response = aparatApi.searchVideosByCategory(query, PER_PAGE_COUNT,position,uniqueId)
            val aparatVideos = response.searchedVideo
            val ui = response.pagingHelperParameters

            val backPageOffset = ui.getBackPageOffset()
            val forwardPageOffset = ui.getForwardPageOffset()
            if(aparatVideos!=null){

                LoadResult.Page(
                    data = aparatVideos,
                    prevKey =  backPageOffset,
                    nextKey =  forwardPageOffset
                )
            }
            else{
                val emptyAparatVideo :List<Video> = emptyList()

                LoadResult.Page(
                    data = emptyAparatVideo,
                    prevKey =  backPageOffset,
                    nextKey =  forwardPageOffset
                )
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

     override fun getRefreshKey(state: PagingState<Int, Video>): Int? {
         TODO("Not yet implemented")
     }
 }