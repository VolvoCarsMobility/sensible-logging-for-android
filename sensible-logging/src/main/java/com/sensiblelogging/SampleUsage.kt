package com.sensiblelogging

import com.sensiblelogging.filter.AllowAllFilter
import com.sensiblelogging.filter.Filter
import com.sensiblelogging.filter.and
import com.sensiblelogging.formatter.SimpleFormatter

class SampleUsage {


    fun setupLogging() {
        val filterCombination = Filter.level(Level.DEBUG) and Filter.categories(listOf("UI"))
        Log.Setup.Builder()
            .addLogCatChannel(filterCombination, SimpleFormatter)
            .addStandardOutChannel(AllowAllFilter, SimpleFormatter)
            .build()
    }
}

object Channels {
    const val LogCat = "LogCat"
    const val StandardOut = "StandardOut"
}
