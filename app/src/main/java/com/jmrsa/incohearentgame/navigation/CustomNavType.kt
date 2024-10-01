package com.jmrsa.incohearentgame.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.jmrsa.incohearentgame.utils.checkBuildVersion
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

open class CustomNavType<T : Parcelable>(
    private val clazz: Class<T>,
    private val serializer: KSerializer<T>,
    isNullableAllowed: Boolean = false
) : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? =
        if (checkBuildVersion()) {
            bundle.getParcelable(key, clazz) as T
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun put(bundle: Bundle, key: String, value: T) =
        bundle.putParcelable(key, value)

    override fun parseValue(value: String): T =
        Json.decodeFromString(serializer, value)

    override fun serializeAsValue(value: T): String =
        Uri.encode(Json.encodeToString(serializer, value))

    override val name: String = clazz.name

}