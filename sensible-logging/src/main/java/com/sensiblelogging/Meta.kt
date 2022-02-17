package com.sensiblelogging

data class Meta(
    val className: String,
    val simpleClassName: String,
    val functionName: String,
    val lineNumber: Int,
    val threadName: String,
    val fileName: String
)
