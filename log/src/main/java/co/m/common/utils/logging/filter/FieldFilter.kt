package co.m.common.utils.logging.filter

import co.m.common.utils.logging.Line

interface FieldFilter<T> : Filter {
    override fun matches(line: Line) = isAllowed(locateField(line))
    fun locateField(line: Line): T
    fun isAllowed(field: T): Boolean
}
