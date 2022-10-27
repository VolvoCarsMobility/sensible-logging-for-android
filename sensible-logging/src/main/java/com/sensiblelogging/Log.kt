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

import com.sensiblelogging.printer.Channel
import com.sensiblelogging.processor.LogProcessor
import com.sensiblelogging.util.Constants.DEFAULT_CATEGORY
import com.sensiblelogging.util.Constants.DEFAULT_CHANNELS
import com.sensiblelogging.util.Constants.DEFAULT_CHANNEL_LIST
import com.sensiblelogging.util.Constants.DEFAULT_STACK_DEPTH
import com.sensiblelogging.util.Constants.EMPTY_PARAMS

object Log {
    private val processor: LogProcessor = LogProcessor()

    fun v(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.VERBOSE, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun v(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.VERBOSE, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun d(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.DEBUG, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun d(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.DEBUG, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun i(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.INFO, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun i(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.INFO, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        throwable: Throwable? = null,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun w(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        throwable: Throwable? = null,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    fun e(
        message: String,
        category: String = DEFAULT_CATEGORY,
        vararg channels: String = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    fun log(
        level: Level = Level.DEBUG,
        message: String,
        preFormattedMessage: Boolean,
        category: String = DEFAULT_CATEGORY,
        channels: List<String> = DEFAULT_CHANNEL_LIST,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        stackDepth: Int = DEFAULT_STACK_DEPTH
    ) = processor.log(level, message, preFormattedMessage, category, channels, throwable, parameters, stackDepth)

    fun removePrinters(vararg channels: Channel) {
        processor.removeChannels(channels.toList())
    }

    fun addPrinters(vararg channels: Channel) {
        processor.addChannels(channels.toList())
    }
}
