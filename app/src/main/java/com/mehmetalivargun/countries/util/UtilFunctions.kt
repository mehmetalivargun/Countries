package com.mehmetalivargun.countries.util

import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

fun expand(url: String?): String? {
    var s3: String? = ""
    try {
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        val s: Int = connection.inputStream.read()
        val s2: URL = connection.url
        s3 = java.lang.String.valueOf(s2)
    } catch (e: MalformedURLException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return s3
}