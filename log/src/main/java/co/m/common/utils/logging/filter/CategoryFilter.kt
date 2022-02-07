package co.m.common.utils.logging.filter

import co.m.common.utils.logging.Line

abstract class CategoryFilter : FieldFilter<List<String>> {
    abstract val categories: List<String>
    override fun locateField(line: Line): List<String> = line.categories
    override fun isAllowed(field: List<String>): Boolean = categories.any { field.contains(it) }
}
