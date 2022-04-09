package com.rymon.aparatdemo.data.search

data class AparatPagingHelper(
    val pagingBack: String?,
    val pagingForward: String?
) {
    fun getBackPage(): Int? {
        return if(pagingBack!=null && pagingBack!="")
           parsUrl(pagingBack.toString())
         else
            null
    }

    private fun parsUrl(urlString: String): Int? {
        return urlString.substringAfterLast("/").toInt()
    }


    fun getForwardPage(): Int? {
        return if(pagingForward!=null && pagingForward!="")
             parsUrl(pagingForward.toString())
        else
            null
    }
    private fun getBetweenString(urlString: String , prefix:String,suffix:String){
        val result = urlString.substringAfter(prefix).substringBefore(suffix)
    }
}