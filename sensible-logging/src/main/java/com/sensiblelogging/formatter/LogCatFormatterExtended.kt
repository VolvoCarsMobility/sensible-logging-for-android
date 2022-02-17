package com.sensiblelogging.formatter

import com.sensiblelogging.Line
import com.sensiblelogging.Meta

object LogCatFormatterExtended : Formatter {

    private const val threadMaxLength = 16
    private const val categoriesMaxLength = 14
    private const val fileAndFunctionMaxLength = 42
    private const val messageMaxLength = 64
    private const val trafficLightEmoji = "\uD83D\uDEA6"

    override fun format(line: Line, meta: Meta): String =
        "$trafficLightEmoji${formatCategories(line)} | ${threadName(meta)} | ${fileAndFunction(meta)} | ${
            message(
                line,
                line.parameters.isNotEmpty()
            )
        }${formatParameters(line)?.let { " | $it" } ?: ""}"

    private fun message(line: Line, normalizeLength: Boolean) =
        if (normalizeLength) line.message.normalizeLength(messageMaxLength) else line.message

    private fun threadName(meta: Meta) = meta.threadName.normalizeLength(threadMaxLength)

    private fun fileAndFunction(meta: Meta) =
        "${meta.fileName}:${meta.functionName}"
            .normalizeLength(fileAndFunctionMaxLength)

    private fun formatParameters(line: Line): String? =
        line.parameters.takeIf { it.isNotEmpty() }?.entries?.joinToString { "[\"${it.key}\"]: ${it.value}" }

    private fun formatCategories(line: Line): String {
        return "[${line.categories.joinToString().normalizeLength(categoriesMaxLength, "")}]".normalizeLength(
            categoriesMaxLength + 4,
            " ",
            ""
        )
    }

    private fun String.normalizeLength(
        characterCount: Int,
        normalizationWhiteSpace: String = "\u2007",
        ellipsis: String = "…"
    ): String = when {
        length > characterCount -> {
            substring(0, characterCount - 1) + ellipsis
        }
        length < characterCount -> {
            this + normalizationWhiteSpace.repeat(characterCount - length)
        }
        else -> {
            this
        }
    }
}
