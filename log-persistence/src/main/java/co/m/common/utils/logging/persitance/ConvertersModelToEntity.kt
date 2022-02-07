package co.m.common.utils.logging.persitance

import co.m.common.utils.logging.Level
import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta

fun Line.toEntity(meta: Meta) = LogEntity(
    timestamp = timestamp,
    categories = categories,
    preFormatted = preFormatted,
    level = level.toLevel(),
    message = message,
    parameters = parameters,
    throwable = throwable,
    meta = meta.toMetaEntity()
)

fun Level.toLevel() = LogEntity.Level.values()[ordinal]
fun Meta.toMetaEntity() =
    LogEntity.MetaEntity(
        className,
        simpleClassName,
        functionName,
        lineNumber,
        threadName,
        fileName
    )

fun LogEntity.MetaEntity.toMeta() = Meta(className, simpleClassName, functionName, lineNumber, threadName, fileName)

fun LogEntity.toLine(): Line =
    Line(
        timestamp,
        categories,
        Level.valueOf(level.name),
        message,
        preFormatted,
        throwable,
        parameters.orEmpty()
    )
