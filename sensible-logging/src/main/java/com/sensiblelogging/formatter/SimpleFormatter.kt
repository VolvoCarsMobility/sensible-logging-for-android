package com.sensiblelogging.formatter

import com.sensiblelogging.Line
import com.sensiblelogging.Meta
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object SimpleFormatter : Formatter {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH)

    override fun format(line: Line, meta: Meta): String {
        val stackTrace = line.throwable?.let {
            " exception[${it.message}]\n${it.stackTraceToString()}"
        } ?: ""
        return "${dateFormatter.format(Date(line.timestamp))} [${line.level.name}] [${line.categories.joinToString()}] ${meta.threadName} ${meta.fileName}:${meta.functionName}    -    ${line.message} ${line.parameters.entries.joinToString { "[${it.key}]:${it.value}" }}$stackTrace"
    }

    private fun Throwable.stackTraceToString(): String {
        val sw = StringWriter()
        printStackTrace(PrintWriter(sw))
        return sw.toString()
    }
}
