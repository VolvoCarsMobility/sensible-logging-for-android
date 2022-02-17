package com.sensiblelogging.printer

import com.sensiblelogging.Line
import com.sensiblelogging.Meta
import com.sensiblelogging.filter.Filter

abstract class Printer {
    abstract val filter: Filter
    fun print(line: Line, meta: Meta) {
        if (filter.matches(line)) {
            printFiltered(line, meta)
        }
    }

    abstract fun printFiltered(line: Line, meta: Meta)
}
