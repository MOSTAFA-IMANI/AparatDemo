package com.rymon.aparatdemo.data.search

import com.rymon.aparatdemo.utils.findParameterInUrl

data class AparatPagingHelper(
    val pagingBack: String?,
    val pagingForward: String?
) {
    fun getBackPageOffset(): Int? {
        return if(pagingBack!=null && pagingBack!="")
           try {
               pagingBack.findParameterInUrl("curoffset")?.toInt()
           }catch (e:Exception){
               null
           }
         else
            null
    }

    private fun parsUrl(urlString: String): Int? {
        return urlString.substringAfterLast("/").toInt()
    }


    fun getForwardPageOffset(): Int? {
        return if(pagingForward!=null && pagingForward!="")
            try {

                pagingForward.findParameterInUrl("curoffset")?.toInt()
            }catch (e:Exception){
                null
            }
        else
            null
    }

}