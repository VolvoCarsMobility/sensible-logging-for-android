package com.sensiblelogging.filter

import com.sensiblelogging.Line

interface Filter {
    fun matches(line: Line): Boolean
}
