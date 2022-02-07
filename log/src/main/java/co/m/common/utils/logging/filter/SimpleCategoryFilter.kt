package co.m.common.utils.logging.filter

class SimpleCategoryFilter(override val categories: List<String>) : CategoryFilter() {
    constructor(vararg categories: String) : this(categories.toList())
}
