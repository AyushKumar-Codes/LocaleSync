package io.github.ayushkumar.localSync.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.emptyMap

@Singleton
class LocalizationStorage @Inject constructor(@ApplicationContext val context: Context){
    private val prefs: SharedPreferences =
        context.getSharedPreferences("localization_prefs", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveLocalization(data: Map<String, Map<String, String>>) {
        val json = gson.toJson(data)
        prefs.edit().putString("localization_json", json).apply()
    }

    fun getLocalization(): Map<String, Map<String, String>> {
        val json = prefs.getString("localization_json", null) ?: return emptyMap()
        return gson.fromJson(json, Map::class.java) as Map<String, Map<String, String>>
    }


}