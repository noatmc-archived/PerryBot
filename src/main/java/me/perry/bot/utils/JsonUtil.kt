package me.perry.bot.utils

import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author perry.
 * @since 2/9/2022.
 * Class for handling/using Google's GSON Library. Mostly used here for its useful JSON features.
 */
object JsonUtil {

    /**
     * @param url    The URL to parse from.
     * @param object The Object to parse from JSON.
     * @return The value of the Object if it exists.
     */
    fun parse(url: String?, `object`: String?): String {
        val result: String
        val request = URL(url).openConnection() as HttpURLConnection
        request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)")
        request.connect()
        result =
            JsonParser.parseReader(BufferedReader(InputStreamReader(request.inputStream))).asJsonObject[`object`].asString
        return result
    }

    /**
     * @param url    The URL to parse from.
     * @param array  The Array to parse through.
     * @param object The Object to parse from JSON.
     * @return The value of an Object inside an Array if both exist.
     */
    fun parseArray(url: String?, array: String?, `object`: String?): String {
        val result: String
        val request = URL(url).openConnection() as HttpURLConnection
        request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)")
        request.connect()
        result =
            JsonParser.parseReader(BufferedReader(InputStreamReader(request.inputStream))).asJsonObject.getAsJsonObject(
                array
            )[`object`].toString()
        return result
    }

    /**
     * @param userName Minecraft username to input.
     * @return The UUID from the userName input if the Minecraft user exists.
     */
    fun getUUIDFromName(userName: String): String {
        var result: String
        val request =
            URL("https://api.mojang.com/users/profiles/minecraft/$userName").openConnection() as HttpURLConnection
        request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)")
        request.connect()
        result =
            JsonParser.parseReader(BufferedReader(InputStreamReader(request.inputStream))).asJsonObject["id"].asString
        result = StringBuilder(result).insert(result.length - 12, "-").insert(result.length - 16, "-")
            .insert(result.length - 20, "-").insert(result.length - 24, "-").toString()
        return result
    }
}