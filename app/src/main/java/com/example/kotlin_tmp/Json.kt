package com.example.kotlin_tmp

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Json {
    val api = Api()
    val gson = Gson()
    inline fun <reified T, E>get(site: String): E {
        return gson.fromJson(api.get(site), object : TypeToken<T>(){}.type)
    }
}