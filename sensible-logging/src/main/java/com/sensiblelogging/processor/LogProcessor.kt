/*
 * Copyright 2022 Volvo Car Mobility AB
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

package com.sensiblelogging.processor

import com.sensiblelogging.Level
import com.sensiblelogging.Line
import com.sensiblelogging.printer.Printer
import com.sensiblelogging.util.LogUtil

internal class LogProcessor {

    private var printers = ArrayList<Printer>()

    @Volatile
    private var printingList = emptyArray<Printer>()

    fun addPrinters(vararg printer: Printer) {
        synchronized(printers) {
            printers.addAll(printer)
            printingList = printers.toTypedArray()
        }
    }

    fun removePrinters(vararg printer: Printer) {
        synchronized(printers) {
            printers.removeAll(printer)
            printingList = printers.toTypedArray()
        }
    }

    fun log(
        level: Level,
        message: String,
        preFormattedMessage: Boolean,
        categories: List<String>,
        throwable: Throwable?,
        parameters: Map<String, String>,
        stackDepth: Int
    ) {
        val line = Line(
            System.currentTimeMillis(),
            categories,
            level,
            message,
            preFormattedMessage,
            throwable,
            parameters
        )
        if (printingList.any { it.filter.matches(line) }) {
            val meta = LogUtil.gatherMeta(stackDepth)
            printingList.forEach { it.print(line, meta) }
        }
    }
}
