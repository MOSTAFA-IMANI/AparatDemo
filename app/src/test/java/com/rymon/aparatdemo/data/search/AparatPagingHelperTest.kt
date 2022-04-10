package com.rymon.aparatdemo.data.search

import com.google.common.truth.Truth.assertThat
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
    val TEST_URL_3 = "https://www.aparat.com//etc/api/curoffset/10d/id/44061669/moretype/forward"

    @Test
    fun `getBackPageOffset must return null with an empty input`(){
        val result =  AparatPagingHelper("","").getBackPageOffset()
        assertThat(result).isEqualTo(null)
    }
    @Test
    fun `getForwardPageOffset must return null with an empty input`(){
        val result =  AparatPagingHelper("","").getForwardPageOffset()
        assertThat(result).isEqualTo(null)
    }
    @Test
    fun `getBackPageOffset must return null with null input`(){
        val result =  AparatPagingHelper(null,null).getBackPageOffset()
        assertThat(result).isEqualTo(null)
    }
    @Test
    fun `getForwardPageOffset must return null with null input`(){
        val result =  AparatPagingHelper(null,null).getForwardPageOffset()
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `getBackPageOffset must find the back offset int`(){
        val result =  AparatPagingHelper(TEST_URL_1,"").getBackPageOffset()
        assertThat(result).isEqualTo(9)

        val result2 =  AparatPagingHelper(TEST_URL_2,"").getBackPageOffset()
        assertThat(result2).isEqualTo(10)
    }

    @Test
    fun `getForwardPageOffset must find the back offset int`(){
        val result =  AparatPagingHelper("",TEST_URL_1).getForwardPageOffset()
        assertThat(result).isEqualTo(9)

        val result2 =  AparatPagingHelper("",TEST_URL_2).getForwardPageOffset()
        assertThat(result2).isEqualTo(10)
    }

    @Test
    fun `getForwardPageOffset and getBackPageOffset must return null when there is no currofset key`(){
        val result =  AparatPagingHelper("",
            "TEST_URL_WITHOUT_KEY").getForwardPageOffset()
        assertThat(result).isEqualTo(null)

        val result2 =  AparatPagingHelper("TEST_URL_WITHOUT_KEY",
            "").getBackPageOffset()
        assertThat(result2).isEqualTo(null)
    }

    @Test
    fun `getForwardPageOffset and getBackPageOffset must return null when there is no digit on return`(){

        // this case never should be happened if backend Api work in good way

        val result =  AparatPagingHelper("",
            TEST_URL_3).getForwardPageOffset()
        assertThat(result).isEqualTo(null)

        val result2 =  AparatPagingHelper(TEST_URL_3,
            "").getBackPageOffset()
        assertThat(result2).isEqualTo(null)
    }

}