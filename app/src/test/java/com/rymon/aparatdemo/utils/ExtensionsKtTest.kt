package com.rymon.aparatdemo.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExtensionsKtTest {
    private val TEST_URL_1 =
        "https://www.aparat.com//etc/api/videobysearch/text/perspolis/perpage/10/curoffset/9"
    private val TEST_URL_2 =
        "https://www.aparat.com//etc/api/categoryvideos/cat/7/perpage/10/moretype/forward/curoffset/10/id/44061669/moretype/forward"

    /********************************************************************************************
     * findParameterInUrl() is an Extension function
     * which used for get path segment
     * *****************************************************************************************/


    @Test
    fun `findParameterInUrl() must return null with empty input`() {

        val result = TEST_URL_1.findParameterInUrl("")
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `findParameterInUrl() must return null when can't find segment`() {

        val result = TEST_URL_1.findParameterInUrl("NOT_EXIST_TAG")
        val a2 = 2
        assertThat(result).isEqualTo(null)
        val a = 2
    }

    @Test
    fun `findParameterInUrl() must find first segment `() {

        val result1 = TEST_URL_1.findParameterInUrl("text")
        assertThat(result1).isEqualTo("perspolis")

        val result2 = TEST_URL_2.findParameterInUrl("cat")
        assertThat(result2).isEqualTo("7")

    }

    @Test
    fun `findParameterInUrl() must find last segment`() {

        val result = TEST_URL_1.findParameterInUrl("curoffset")
        assertThat(result).isEqualTo("9")

        val result2 = TEST_URL_2.findParameterInUrl("id")
        assertThat(result2).isEqualTo("44061669")

    }

    @Test
    fun `findParameterInUrl() maybe pass the wrong and las segment and more than SegmentArray size`() {

        val result = TEST_URL_1.findParameterInUrl("9")
        assertThat(result).isEqualTo(null)

    }

    /********************************************************************************************
     * convertSecondDuration() is an Extension function
     * which used for convert millisecond to minutes and seconds
     * You know WHAT? After implementing and developing this function I found out SERVER send second :)
     * **Outputs:
     * ** Empty String
     * ** 00:00
     * ** 00:10
     * ** 00:01
     * ** 01:00
     * ** 01:40:00
     * ** 27:46:40
     * *****************************************************************************************/


    @Test
    fun `convertSecondDuration() must return empty output when input less than 0`() {
        val milliseconds: Long = -1L
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `convertSecondDuration() must return correct output when input is less than a second`() {
        val milliseconds: Long = 0L
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("00:00")
    }

    @Test
    fun `convertSecondDuration() must return correct output when input is less than a min`() {
        val milliseconds: Long = 10
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("00:10")

    }

    @Test
    fun `convertSecondDuration() must return correct output when expected output is one digit of second`() {
        val milliseconds: Long = 1
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("00:01")
    }

    @Test
    fun `convertSecondDuration() must return correct output when expected output is one digit of minutes`() {
        val milliseconds: Long = 60
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("01:00")

    }


    @Test
    fun `convertSecondDuration() must return correct output when input is bigger than an hour`() {
        val milliseconds: Long = 6000
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("01:40:00")

    }

    @Test
    fun `convertSecondDuration() must return correct output when input is bigger than a day `() {
        val milliseconds: Long = 100000
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("27:46:40")

    }


    @Test
    fun `convertSecondDuration() must return correct output when expected output is 3 digit of hour or more`() {
        val milliseconds: Long = 500000
        val result = milliseconds.convertSecondDuration()
        assertThat(result).isEqualTo("~")
    }


}