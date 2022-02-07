package co.m.common.utils.logging.persitance

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson

class LogDatabaseTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun stringArrayToString(strings: List<String>): String {
        return TextUtils.join(",", strings)
    }

    @TypeConverter
    fun stringToStringArray(strings: String): List<String> {
        return TextUtils.split(strings, ",").toList()
    }

    @TypeConverter
    fun fromLevelToString(Level: LogEntity.Level): String {
        return Level.name
    }

    @TypeConverter
    fun fromStringToLevel(level: String): LogEntity.Level {
        return LogEntity.Level.valueOf(level)
    }

    @TypeConverter
    fun fromMapStringStringMapsToString(map: Map<String, String>): String? {
        return if (map.isNotEmpty()) {
            map.map { "${it.key},${it.value}" }.joinToString(";")
        } else {
            null
        }
    }

    @TypeConverter
    fun fromThrowableToString(throwable: Throwable?): String? {
        return throwable?.let {
            try {
                gson.toJson(it)
            } catch (e: Exception) {
                println("Unable to serialize $throwable")
                e.printStackTrace()
                null
            }
        }
    }

    @TypeConverter
    fun fromStringToThrowable(throwable: String?): Throwable? {
        return throwable?.let {
            try {
                gson.fromJson(it, Throwable::class.java)
            } catch (e: Exception) {
                println("Unable to parse JSON")
                e.printStackTrace()
                null
            }
        }
    }

    @TypeConverter
    fun fromStringToMapStringString(string: String?): Map<String, String> {
        return string
            ?.split(";")
            ?.map { keyValue ->
                keyValue.split(",")
                    .let {
                        it[0] to it[1]
                    }
            }
            ?.toMap() ?: emptyMap()
    }
}
