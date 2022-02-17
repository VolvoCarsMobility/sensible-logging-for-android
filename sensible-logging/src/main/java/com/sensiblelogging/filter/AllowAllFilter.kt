package com.sensiblelogging.filter

import com.sensiblelogging.Line

object AllowAllFilter : Filter {
    override fun matches(line: Line): Boolean = true
}
