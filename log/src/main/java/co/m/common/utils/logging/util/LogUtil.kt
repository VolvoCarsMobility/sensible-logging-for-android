package co.m.common.utils.logging.util

import co.m.common.utils.logging.Meta

object LogUtil {
    fun gatherMeta(stackDepth: Int): Meta {
        val element = Throwable().stackTrace[stackDepth]
        return Meta(
            className = element.className,
            simpleClassName = element.className.substring(element.className.lastIndexOf('.') + 1),
            functionName = element.methodName,
            lineNumber = element.lineNumber,
            threadName = Thread.currentThread().name,
            fileName = element.fileName ?: "n/a"
        )
    }
}
