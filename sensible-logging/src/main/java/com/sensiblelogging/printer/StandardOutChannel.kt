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

package com.sensiblelogging.printer

import com.sensiblelogging.Level
import com.sensiblelogging.Line
import com.sensiblelogging.Meta
import com.sensiblelogging.filter.Filter
import com.sensiblelogging.formatter.Formatter

class StandardOutChannel(
    private val formatter: Formatter,
    override val filter: Filter,
    override val default: Boolean = false,
) : Channel() {

    companion object {
        const val id = "StandardOut"
    }

    override val id: String = Companion.id

    override fun printFiltered(line: Line, meta: Meta) {
        val formattedMessage = if (line.preFormatted) line.message else formatter.format(line, meta)
        if (line.level.ordinal < Level.ERROR.ordinal) {
            System.out.println(formattedMessage)
        } else {
            System.err.println(formattedMessage)
        }
    }
}
