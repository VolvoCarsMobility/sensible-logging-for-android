package co.m.common.utils.logging.formatter

import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta

object LogCatFormatterSimple : Formatter {
    override fun format(line: Line, meta: Meta): String {
        return "${line.message} ${line.parameters.entries.joinToString { "[${it.key}]:${it.value}" }}"
    }
}
