/*
 * Copyright 2022 Volvo Cars Corporation
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

package sh.vcm.sensiblelogging.processor

import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.Level
import sh.vcm.sensiblelogging.Line
import sh.vcm.sensiblelogging.channel.Channel
import sh.vcm.sensiblelogging.util.LogUtil

internal class LogProcessor {

    private var channels = ArrayList<Channel>()

    @Volatile
    private var channelsArray = emptyArray<Channel>()

    fun addChannels(channels: List<Channel>) {
        synchronized(this.channels) {
            this.channels.addAll(channels)
            channelsArray = this.channels.toTypedArray()
        }
    }

    fun removeChannels(channels: List<Channel>) {
        synchronized(this.channels) {
            this.channels.removeAll(channels)
            channelsArray = this.channels.toTypedArray()
        }
    }

    fun log(
        level: Level,
        message: String,
        preFormattedMessage: Boolean,
        category: Category,
        channels: List<Int>,
        throwable: Throwable?,
        parameters: Map<String, String>,
        stackDepth: Int
    ) {
        val line = Line(
            System.currentTimeMillis(),
            category,
            level,
            message,
            preFormattedMessage,
            throwable,
            parameters
        )
        if (channelsArray.any { it.filter.matches(line) }) {
            val meta = LogUtil.gatherMeta(stackDepth)
            channelsArray
                .filter { it.default || channels.contains(it.id) }
                .forEach { it.printFiltered(line, meta) }
        }
    }
}
