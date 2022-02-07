package co.m.common.utils.logging.printer

import co.m.common.utils.logging.Level
import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta
import co.m.common.utils.logging.filter.Filter
import co.m.common.utils.logging.formatter.Formatter

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
