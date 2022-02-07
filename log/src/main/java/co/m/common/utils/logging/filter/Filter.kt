package co.m.common.utils.logging.filter

import co.m.common.utils.logging.Line

interface Filter {
    fun matches(line: Line): Boolean
}
