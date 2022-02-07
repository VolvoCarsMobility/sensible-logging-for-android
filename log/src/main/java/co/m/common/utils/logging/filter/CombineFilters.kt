package co.m.common.utils.logging.filter

import co.m.common.utils.logging.Line

class CombineFilters(private val first: Filter, private val second: Filter) {
    fun toEitherOr() = object : Filter {
        override fun matches(line: Line): Boolean {
            return first.matches(line) || second.matches(line)
        }
    }

    fun toRequireBoth() = object : Filter {
        override fun matches(line: Line): Boolean {
            return first.matches(line) && second.matches(line)
        }
    }
}

infix fun Filter.and(filter: Filter): Filter = CombineFilters(this, filter).toRequireBoth()
infix fun Filter.or(filter: Filter): Filter = CombineFilters(this, filter).toEitherOr()
