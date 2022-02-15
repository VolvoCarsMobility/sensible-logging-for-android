package com.sensiblelogging

data class Line(
    val timestamp: Long,
    val categories: List<String>,
    val level: Level,
    val message: String,
    val preFormatted: Boolean,
    val throwable: Throwable?,
    val parameters: Map<String, String>
)
