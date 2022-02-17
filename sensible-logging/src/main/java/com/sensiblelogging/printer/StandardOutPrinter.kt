package com.sensiblelogging.printer

import com.sensiblelogging.Level
import com.sensiblelogging.Line
import com.sensiblelogging.Meta
import com.sensiblelogging.filter.Filter
import com.sensiblelogging.formatter.Formatter

class StandardOutPrinter(
    private val formatter: Formatter,
    override val filter: Filter
) : Printer() {
    override fun printFiltered(line: Line, meta: Meta) {
        val formattedMessage = if (line.preFormatted) line.message else formatter.format(line, meta)
        if (line.level.ordinal < Level.ERROR.ordinal) {
            System.out.println(formattedMessage)
        } else {
            System.err.println(formattedMessage)
        }
    }
}
