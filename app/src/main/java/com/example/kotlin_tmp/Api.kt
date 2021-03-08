package com.example.kotlin_tmp

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class Api {
    val baseUrl = "http://192.168.11.7/api/"

    fun getConnection(site: String): HttpURLConnection {
        val url = URL(baseUrl + site)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        return connection
    }

    fun get(site: String): String {
        val connection = getConnection(site)
        connection.connect()

        val br = BufferedReader(InputStreamReader(connection.inputStream))
        var line: String? = null
        val sb = StringBuilder()
        for(line in br.readLine()) {
            line.let { sb.append(line) }
        }

        br.close()

        return sb.toString()
    }
}