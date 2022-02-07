package co.m.common.utils.logging.formatter

import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta

interface Formatter {
    fun format(line: Line, meta: Meta): String
}
