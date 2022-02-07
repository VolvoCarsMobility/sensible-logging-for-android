package co.m.common.utils.logging.util

object Constants {
    internal const val DEFAULT_CATEGORY = "Default"
    internal val DEFAULT_CATEGORY_VARARG = arrayOf(DEFAULT_CATEGORY)
    internal val DEFAULT_CATEGORIES: List<String> = listOf(DEFAULT_CATEGORY)
    internal val EMPTY_PARAMS: Map<String, String> = emptyMap()
    internal const val DEFAULT_STACK_DEPTH = 4
}
