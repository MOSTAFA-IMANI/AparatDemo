package com.rymon.aparatdemo.data.models

import com.rymon.aparatdemo.utils.findParameterInUrl

class UrlPathHolder(
    private val urlPath: String?,
){
    enum class ParameterKey(val Key :String) {
        CURRENT_OFFSET("curoffset") ,
        PER_PAGE("perpage"),
        UNIQUE_ID("id");
    }

    fun getPerPage()=
    if(urlPath!=null && urlPath!="")
    try {
        urlPath.findParameterInUrl(ParameterKey.PER_PAGE.Key)?.toInt()
    }catch (e:Exception){
        null
    }
    else
    null



    fun getUniqueId()=
        if(urlPath!=null && urlPath!="")
        try {
            urlPath.findParameterInUrl(UrlPathHolder.ParameterKey.UNIQUE_ID.Key)?.toInt()
        }catch (e:Exception){
            null
        }
    else
        null



    fun getCurrentOffset(): Int? =
        if(urlPath!=null && urlPath!="")
            try {
                urlPath.findParameterInUrl(ParameterKey.CURRENT_OFFSET.Key)?.toInt()
            }catch (e:Exception){
                null
            }
        else
            null


}
