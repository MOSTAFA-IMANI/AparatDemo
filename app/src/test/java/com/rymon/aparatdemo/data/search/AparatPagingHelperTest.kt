package com.rymon.aparatdemo.data.search

import com.google.common.truth.Truth.assertThat
import com.rymon.aparatdemo.data.models.UrlPathHolder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AparatPagingHelperTest{

    /**
     *
     * Aparat Api return a link for next or previous page So to use Paging library must Extract some
     * offset and number to call next api or previous one
     * ...getBackPage
     * ...getForwardPage
     *
     */

    private val TEST_URL_1 = "https://www.aparat.com//etc/api/videobysearch/text/perspolis/perpage/10/curoffset/9"
    private val TEST_URL_2 = "https://www.aparat.com//etc/api/categoryvideos/cat/7/perpage/10/moretype/forward/curoffset/10/id/44061669/moretype/forward"
    private val TEST_URL_3 = "https://www.aparat.com//etc/api/curoffset/10d/id/44061669/moretype/forward"
    private val TEST_URL_4 = "https://www.aparat.com//etc/api/no/10/nothing/44061669/moretype/forward"


    @Test
    fun `getCurrentOffset must return null with an empty input`(){
        val result =  UrlPathHolder("").getCurrentOffset()
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `getCurrentOffset must return null with null input`(){
        val result =  UrlPathHolder(null).getCurrentOffset()
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `getCurrentOffset must find the offset int`(){
        val result =  UrlPathHolder(TEST_URL_1).getCurrentOffset()
        assertThat(result).isEqualTo(9)

        val result2 =  UrlPathHolder(TEST_URL_2).getCurrentOffset()
        assertThat(result2).isEqualTo(10)
    }

    @Test
    fun `getCurrentOffset must return null when there is no curroffset key`(){
        val result =  UrlPathHolder(TEST_URL_4).getCurrentOffset()
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `getCurrentOffset must return null when there is no digit on return`(){

        // this case never should be happened if backend Api work in good way

        val result =  UrlPathHolder(TEST_URL_3).getCurrentOffset()
        assertThat(result).isEqualTo(null)

    }
    // TODO: 4/12/2022 add other method later

}