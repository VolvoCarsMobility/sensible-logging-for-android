package com.sensiblelogging.filter

import com.sensiblelogging.Line

abstract class CategoryFilter : FieldFilter<List<String>> {
    abstract val categories: List<String>
    override fun locateField(line: Line): List<String> = line.categories
    override fun isAllowed(field: List<String>): Boolean = categories.any { field.contains(it) }
}
