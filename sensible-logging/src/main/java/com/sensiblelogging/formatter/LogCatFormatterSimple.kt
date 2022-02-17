package com.sensiblelogging.formatter

import com.sensiblelogging.Line
import com.sensiblelogging.Meta

object LogCatFormatterSimple : Formatter {
    override fun format(line: Line, meta: Meta): String {
        return "${line.message} ${line.parameters.entries.joinToString { "[${it.key}]:${it.value}" }}"
    }
}
