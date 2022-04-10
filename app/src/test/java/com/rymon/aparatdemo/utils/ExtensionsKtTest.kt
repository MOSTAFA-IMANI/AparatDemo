package com.rymon.aparatdemo.utils

import android.net.Uri
import com.google.common.truth.Truth.assertThat
import com.rymon.aparatdemo.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class ExtensionsKtTest {
    private val TEST_URL_1 = "https://www.aparat.com//etc/api/videobysearch/text/perspolis/perpage/10/curoffset/9"
    private val TEST_URL_2 = "https://www.aparat.com//etc/api/categoryvideos/cat/7/perpage/10/moretype/forward/curoffset/10/id/44061669/moretype/forward"
    /**
     * findParameterInUrl() is an Extension function
     * which used for get path segment
     * */


    @Test
    fun `findParameterInUrl() must return null with empty input`() {

        val result  = TEST_URL_1.findParameterInUrl("")
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `findParameterInUrl() must return null when can't find segment`() {

        val result  = TEST_URL_1.findParameterInUrl("NOT_EXIST_TAG")
        val a2 = 2
        assertThat(result).isEqualTo(null)
        val a = 2
    }

    @Test
    fun `findParameterInUrl() must find first segment `() {

        val result1  = TEST_URL_1.findParameterInUrl("text")
        assertThat(result1).isEqualTo("perspolis")

        val result2  = TEST_URL_2.findParameterInUrl("cat")
        assertThat(result2).isEqualTo("7")

    }

    @Test
    fun `findParameterInUrl() must find last segment`() {

        val result  = TEST_URL_1.findParameterInUrl("curoffset")
        assertThat(result).isEqualTo("9")

        val result2  = TEST_URL_2.findParameterInUrl("id")
        assertThat(result2).isEqualTo("44061669")

    }

    @Test
    fun `findParameterInUrl() maybe pass the wrong and las segment and more than SegmentArray size`() {

        val result  = TEST_URL_1.findParameterInUrl("9")
        assertThat(result).isEqualTo(null)

    }

}