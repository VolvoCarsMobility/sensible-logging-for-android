package co.m.common.utils.logging.filter

import co.m.common.utils.logging.Level
import co.m.common.utils.logging.Line

abstract class LogLevelFilter : FieldFilter<Level> {
    abstract val level: Level
    override fun locateField(line: Line): Level = line.level
    override fun isAllowed(field: Level): Boolean = field.ordinal >= level.ordinal
}
