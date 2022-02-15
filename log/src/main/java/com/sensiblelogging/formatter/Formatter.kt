package com.sensiblelogging.formatter

import com.sensiblelogging.Line
import com.sensiblelogging.Meta

interface Formatter {
    fun format(line: Line, meta: Meta): String
}
