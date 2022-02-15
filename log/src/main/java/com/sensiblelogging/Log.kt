/* *
 * Copyright 2022 Volvo Car Mobility AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sensiblelogging

import com.sensiblelogging.printer.Printer
import com.sensiblelogging.processor.LogProcessor
import com.sensiblelogging.util.Constants.DEFAULT_CATEGORIES
import com.sensiblelogging.util.Constants.DEFAULT_CATEGORY_VARARG
import com.sensiblelogging.util.Constants.DEFAULT_STACK_DEPTH
import com.sensiblelogging.util.Constants.EMPTY_PARAMS

object Log {
    private val processor: LogProcessor = LogProcessor()

    fun v(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.VERBOSE, message, false, categories.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun v(
        message: String,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.VERBOSE, message, false, categories.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun d(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.DEBUG, message, false, categories.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun d(
        message: String,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.DEBUG, message, false, categories.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun i(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.INFO, message, false, categories.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun i(
        message: String,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.INFO, message, false, categories.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.WARN, message, false, categories.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.WARN, message, false, categories.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        throwable: Throwable? = null,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.WARN, message, false, categories.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.WARN, message, false, categories.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.ERROR, message, false, categories.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        throwable: Throwable? = null,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.ERROR, message, false, categories.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.ERROR, message, false, categories.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        vararg categories: String = DEFAULT_CATEGORY_VARARG
    ) = processor.log(Level.ERROR, message, false, categories.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun log(
        level: Level = Level.DEBUG,
        message: String,
        preFormattedMessage: Boolean,
        categories: List<String> = DEFAULT_CATEGORIES,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        stackDepth: Int = DEFAULT_STACK_DEPTH
    ) = processor.log(level, message, preFormattedMessage, categories, throwable, parameters, stackDepth)

    fun removePrinters(vararg printer: Printer) {
        processor.removePrinters(*printer)
    }

    fun addPrinters(vararg printer: Printer) {
        processor.addPrinters(*printer)
    }
}
