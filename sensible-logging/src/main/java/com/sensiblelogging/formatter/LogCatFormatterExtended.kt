/*
 * Copyright 2022 Volvo Cars Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sensiblelogging.formatter

import com.sensiblelogging.Level
import com.sensiblelogging.Line
import com.sensiblelogging.Meta

object LogCatFormatterExtended : Formatter {

    private const val threadMaxLength = 16
    private const val categoriesMaxLength = 14
    private const val fileAndFunctionMaxLength = 42
    private const val messageMaxLength = 64

    private val verboseEmoji = Character.toChars(0x26AB).concatToString()
    private val debugEmoji = Character.toChars(0x1F7E2).concatToString()
    private val infoEmoji = Character.toChars(0x26AA).concatToString()
    private val warnEmoji = Character.toChars(0x2620).concatToString()
    private val errorEmoji = Character.toChars(0x26D4).concatToString()
    private val assertEmoji = Character.toChars(0x2623).concatToString()

    override fun format(line: Line, meta: Meta): String =
        "${line.level.asEmoji()} ${formatCategories(line)} --- ${threadName(meta)} : ${fileAndFunction(meta)} : ${
            message(
                line,
                line.parameters.isNotEmpty()
            )
        }${formatParameters(line)?.let { " : $it" } ?: ""}"

    private fun message(line: Line, normalizeLength: Boolean) =
        if (normalizeLength) line.message.normalizeLength(messageMaxLength) else line.message

    private fun threadName(meta: Meta) = "[${
        meta.threadName.normalizeLength(
            characterCount = threadMaxLength,
            normalizationAlignment = NormalizationAlignment.Right
        )
    }]"

    private fun fileAndFunction(meta: Meta) =
        "${meta.simpleClassName}${meta.functionName.asMethodOrFunction()}"
            .normalizeLength(fileAndFunctionMaxLength)

    private fun formatParameters(line: Line): String? =
        line.parameters.takeIf { it.isNotEmpty() }?.entries?.joinToString { "${it.key}=\"${it.value}\"" }

    private fun formatCategories(line: Line): String {
        return line.category.name.normalizeLength(categoriesMaxLength)
    }

    private fun Level.asEmoji() = when (this) {
        Level.VERBOSE -> verboseEmoji
        Level.DEBUG -> debugEmoji
        Level.INFO -> infoEmoji
        Level.WARN -> warnEmoji
        Level.ERROR -> errorEmoji
        Level.ASSERT -> assertEmoji
    }

    private enum class NormalizationAlignment {
        Left,
        Right
    }

    private fun String.normalizeLength(
        characterCount: Int,
        normalizationWhiteSpace: String = "\u2007",
        ellipsis: String = "…",
        normalizationAlignment: NormalizationAlignment = NormalizationAlignment.Left
    ): String = when {
        length > characterCount -> {
            substring(0, characterCount - 1) + ellipsis
        }
        length < characterCount -> {

            when (normalizationAlignment) {
                NormalizationAlignment.Left -> this + normalizationWhiteSpace.repeat(characterCount - length)
                NormalizationAlignment.Right -> normalizationWhiteSpace.repeat(characterCount - length) + this
            }
        }
        else -> {
            this
        }
    }

    private fun String.asMethodOrFunction() =
        if (this.contains("lambda")) " () -> {}" else ".${this}(…)"
}
