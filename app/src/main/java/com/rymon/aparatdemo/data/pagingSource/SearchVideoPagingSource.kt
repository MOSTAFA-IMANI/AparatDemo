package com.rymon.aparatdemo.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rymon.aparatdemo.api.AparatApi
import com.rymon.aparatdemo.data.models.UrlPathHolder
import com.rymon.aparatdemo.data.models.Video
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_OFFSET = 0
private const val PER_PAGE_COUNT = 10



 class SearchVideoPagingSource(
    private val aparatApi: AparatApi,
    private val query: String
) : PagingSource<Int, Video>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Video> {
        val position = params.key ?: STARTING_PAGE_OFFSET

        return try {

            val response = aparatApi.searchVideos(query, PER_PAGE_COUNT,position)
            val aparatVideos = response.searchedVideo
            val ui = response.pagingHelperParameters

            val backPageOffset = UrlPathHolder(ui.pagingBack).getCurrentOffset()
            val forwardPageOffset = UrlPathHolder(ui.pagingForward).getCurrentOffset()
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
         val a = state

         TODO("Not yet implemented")
     }
 }