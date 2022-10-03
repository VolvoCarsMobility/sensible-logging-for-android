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

import com.sensiblelogging.filter.AllowAllFilter
import com.sensiblelogging.filter.Filter
import com.sensiblelogging.formatter.Formatter
import com.sensiblelogging.formatter.LogCatFormatterSimple
import com.sensiblelogging.formatter.SimpleFormatter
import com.sensiblelogging.printer.LogCatPrinter
import com.sensiblelogging.printer.Printer
import com.sensiblelogging.printer.PrinterWrapper
import com.sensiblelogging.printer.StandardOutPrinter
import com.sensiblelogging.processor.LogProcessor
import com.sensiblelogging.util.Constants.DEFAULT_CATEGORY
import com.sensiblelogging.util.Constants.DEFAULT_CHANNEL
import com.sensiblelogging.util.Constants.DEFAULT_CHANNELS
import com.sensiblelogging.util.Constants.DEFAULT_STACK_DEPTH
import com.sensiblelogging.util.Constants.EMPTY_PARAMS

object Log {
    private val processor: LogProcessor = LogProcessor()

    fun v(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.VERBOSE, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun v(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.VERBOSE, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun d(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.DEBUG, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun d(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.DEBUG, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun i(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.INFO, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun i(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.INFO, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        throwable: Throwable? = null,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        throwable: Throwable? = null,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNEL
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun log(
        level: Level = Level.DEBUG,
        message: String,
        preFormattedMessage: Boolean,
        category: String = DEFAULT_CATEGORY,
        channels: List<String> = DEFAULT_CHANNELS,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        stackDepth: Int = DEFAULT_STACK_DEPTH
    ) = processor.log(level, message, preFormattedMessage, category, channels, throwable, parameters, stackDepth)

    object Setup {
        class Builder {
            private val printers = mutableListOf<PrinterWrapper>()

            fun addLogCatChannel(filter: Filter = AllowAllFilter, formatter: Formatter = LogCatFormatterSimple): Builder {
                printers += PrinterWrapper(LogCatPrinter(formatter, filter), "LogCat", true)
                return this
            }

            fun addStandardOutChannel(filter: Filter = AllowAllFilter, formatter: Formatter = SimpleFormatter): Builder {
                printers += PrinterWrapper(StandardOutPrinter(formatter, filter), "StandardOut", false)
                return this
            }

            fun addChannel(channel: String, printer: Printer): Builder {
                printers += PrinterWrapper(printer, channel, false)
                return this
            }

            fun addDefaultChannel(channel: String, printer: Printer): Builder {
                printers += PrinterWrapper(printer, channel, true)
                return this
            }

            fun build() {
                processor.addPrinters(printers)
            }
        }
    }
}
