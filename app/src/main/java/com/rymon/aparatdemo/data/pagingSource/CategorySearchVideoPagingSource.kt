package com.rymon.aparatdemo.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rymon.aparatdemo.api.AparatApi
import com.rymon.aparatdemo.data.models.UrlPathHolder
import com.rymon.aparatdemo.data.models.Video
import com.rymon.aparatdemo.data.search.AparatPagingHelper
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_OFFSET = 0
private const val STARTING_PAGE_UNIQUE_ID = 0
private const val PER_PAGE_COUNT = 10



 class CategorySearchVideoPagingSource(
    private val aparatApi: AparatApi,
    private val categoryId: Int
) : PagingSource<UrlPathHolder, Video>() {

    override suspend fun load(params: LoadParams<UrlPathHolder>): LoadResult<UrlPathHolder, Video> {
        val position = params.key?.getCurrentOffset() ?: STARTING_PAGE_OFFSET
        val uniqueId = params.key?.getUniqueId() ?: STARTING_PAGE_UNIQUE_ID
        return try {

            val response = aparatApi.searchVideosByCategory(categoryId, PER_PAGE_COUNT,position,uniqueId)
            val aparatVideos = response.categoryVideo
            val ui = response.pagingHelperParameters

            val backPageOffset = UrlPathHolder(ui.pagingBack)
            val forwardPageOffset = UrlPathHolder(ui.pagingForward)
            if(aparatVideos!=null){

                LoadResult.Page(
                    data = aparatVideos,
                    prevKey =  forwardPageOffset,
                    nextKey =  backPageOffset
                )
            }
            else{
                val emptyAparatVideo :List<Video> = emptyList()

                LoadResult.Page(
                    data = emptyAparatVideo,
                    prevKey =  forwardPageOffset,
                    nextKey =  backPageOffset
                )
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

     override fun getRefreshKey(state: PagingState<UrlPathHolder, Video>): UrlPathHolder? {
         val a = state

         TODO("Not yet implemented")
     }
 }