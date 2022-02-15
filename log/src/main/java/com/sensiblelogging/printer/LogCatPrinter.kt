package com.sensiblelogging.printer

import android.util.Log.d
import android.util.Log.e
import android.util.Log.i
import android.util.Log.v
import android.util.Log.w
import com.sensiblelogging.Level
import com.sensiblelogging.Line
import com.sensiblelogging.Meta
import com.sensiblelogging.filter.Filter
import com.sensiblelogging.formatter.Formatter

class LogCatPrinter(
    private val formatter: Formatter,
    override val filter: Filter
) : Printer() {

    companion object {
        const val TAG = "Log"
    }

    override fun printFiltered(line: Line, meta: Meta) {
        val formattedMessage = if (line.preFormatted) line.message else formatter.format(line, meta)
        val error: Throwable? = line.throwable
        when (line.level) {
            Level.VERBOSE -> v(TAG, formattedMessage, error)
            Level.DEBUG -> d(TAG, formattedMessage, error)
            Level.INFO -> i(TAG, formattedMessage, error)
            Level.WARN -> w(TAG, formattedMessage, error)
            Level.ERROR -> e(TAG, formattedMessage, error)
            Level.ASSERT -> e(TAG, formattedMessage, error)
        }
    }
}
