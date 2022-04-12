package com.rymon.aparatdemo.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rymon.aparatdemo.api.AparatApi
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_OFFSET = 0
private const val TAG_ID = 1



 class HomeVideoPagingSource(
    private val aparatApi: AparatApi
) : PagingSource<Int, HomeVideoIncluded>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeVideoIncluded> {
        var position = params.key ?: STARTING_PAGE_OFFSET

        return try {

            val response = aparatApi.getHomeVideo()
//            val homeVideoData = response.included
            val emptyAparatVideo :List<HomeVideoIncluded> = emptyList()

//            val homeVideoMetaData = response.data
            val liiink = response.body()?.links
//            val queryNextLink = response.links
            val a = 0

                LoadResult.Page(
                    emptyAparatVideo,
                      position--,
                      position++
                )


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

     override fun getRefreshKey(state: PagingState<Int, HomeVideoIncluded>): Int? {
         val a = state
         TODO("Not yet implemented")
     }
 }