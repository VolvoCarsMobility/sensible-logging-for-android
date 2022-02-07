package co.m.common.utils.logging.filter

import co.m.common.utils.logging.Line

object AllowAllFilter : Filter {
    override fun matches(line: Line): Boolean = true
}
