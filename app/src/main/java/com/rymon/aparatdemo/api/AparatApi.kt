package com.rymon.aparatdemo.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AparatApi {

    companion object {
        const val BASE_URL = "https://www.aparat.com/etc/api/"
//        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }
    // TODO: 12/21/2021 remove extra info

//    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
@GET("videoBySearch/text/{query}/perpage/{per_page}/curoffset/{curoffset}")
suspend fun searchVideos(
    @Path("query") query: String,
    @Path("per_page") perPage: Int,
    @Path("curoffset") pageOffset: Int,
): SearchResponse

    @GET("https://www.aparat.com/api/fa/v1/video/video/list/tagid/1")
    suspend fun searchVideos2(
        @Query("nextq") NextLink: String,
//        @Path("per_page") perPage: Int,
//        @Path("curoffset") pageOffset: Int,
    ): MainHomeResponse


    @GET("https://www.aparat.com/api/fa/v1/video/video/list/tagid/1")
    suspend fun getHomeVideo(
//        @Path("query_next") queryNext: String,
//        @Path("tag_id") tagId: Int,
    ): Response<HomeResponse>

    @GET("categories")
    suspend fun getAllCategory(): Response<MainCategoryResponse>

    @GET(" categoryVideos/cat/{category_id}/perpage/{per_page}/curoffset/{curoffset}/id/{u_id}/moretype/forward")
    suspend fun searchVideosByCategory(
        @Path("category_id") categoryId: Int,
        @Path("per_page") perPage: Int,
        @Path("curoffset") pageOffset: Int,
        @Path("u_id") uniqueId: Int
    ): SubCategoryResponse

}