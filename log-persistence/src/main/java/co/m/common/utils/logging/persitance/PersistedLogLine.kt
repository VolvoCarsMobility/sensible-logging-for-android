package co.m.common.utils.logging.persitance

import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta

data class PersistedLogLine(val line: Line, val meta: Meta)
