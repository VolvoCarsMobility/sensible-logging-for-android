package com.sensiblelogging.filter

import com.sensiblelogging.Level
import com.sensiblelogging.Line

abstract class LogLevelFilter : FieldFilter<Level> {
    abstract val level: Level
    override fun locateField(line: Line): Level = line.level
    override fun isAllowed(field: Level): Boolean = field.ordinal >= level.ordinal
}
