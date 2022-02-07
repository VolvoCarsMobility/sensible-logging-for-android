package co.m.common.utils.logging.printer

import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta
import co.m.common.utils.logging.filter.Filter

abstract class Printer {
    abstract val filter: Filter
    fun print(line: Line, meta: Meta) {
        if (filter.matches(line)) {
            printFiltered(line, meta)
        }
    }

    abstract fun printFiltered(line: Line, meta: Meta)
}
