package com.sensiblelogging.filter

import android.content.SharedPreferences
import com.sensiblelogging.util.Constants.DEFAULT_CATEGORY

class SharedPreferencesCategoryFilter(
    private val preferences: SharedPreferences,
    private val stringArrayPreferenceKey: String,
    private val defaultCategories: Set<String> = setOf(DEFAULT_CATEGORY)
) : CategoryFilter() {

    private lateinit var _categories: List<String>
    val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == stringArrayPreferenceKey) {
            readValue()
        }
    }

    init {
        readValue()
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override val categories: List<String>
        get() = _categories

    private fun readValue() {
        _categories = preferences.getStringSet(stringArrayPreferenceKey, defaultCategories)?.toList().orEmpty()
    }
}
