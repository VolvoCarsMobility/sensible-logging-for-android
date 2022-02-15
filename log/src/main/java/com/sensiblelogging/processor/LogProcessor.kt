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
