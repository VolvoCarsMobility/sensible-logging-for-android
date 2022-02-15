package com.sensiblelogging.filter

import com.sensiblelogging.Line

interface FieldFilter<T> : Filter {
    override fun matches(line: Line) = isAllowed(locateField(line))
    fun locateField(line: Line): T
    fun isAllowed(field: T): Boolean
}
