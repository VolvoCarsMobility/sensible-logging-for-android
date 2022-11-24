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

package sh.vcm.sensiblelogging

import android.app.Application
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.ProcessLifecycleOwner
import sh.vcm.sensiblelogging.lifecycle.ActivityLifecycleLoggingInserter
import sh.vcm.sensiblelogging.lifecycle.DEFAULT_SEPARATOR
import sh.vcm.sensiblelogging.lifecycle.FragmentLifecycleLoggingInserter
import sh.vcm.sensiblelogging.lifecycle.ProcessLifecycleLogger
import sh.vcm.sensiblelogging.lifecycle.ServiceLifecycleLogger
import sh.vcm.sensiblelogging.lifecycle.TYPE_ACTIVITY
import sh.vcm.sensiblelogging.lifecycle.TYPE_FRAGMENT
import sh.vcm.sensiblelogging.lifecycle.TYPE_PROCESS
import sh.vcm.sensiblelogging.lifecycle.TYPE_SERVICE
import sh.vcm.sensiblelogging.lifecycle.processIdentifier
import sh.vcm.sensiblelogging.channel.LogCatChannel

fun Int.toLogLevel(): Level =
    when (this) {
        Log.VERBOSE -> Level.VERBOSE
        Log.DEBUG -> Level.DEBUG
        Log.INFO -> Level.INFO
        Log.WARN -> Level.WARN
        Log.ERROR -> Level.ERROR
        Log.ASSERT -> Level.ASSERT
        else -> throw IllegalStateException("Unknown priority: $this")
    }

fun Level.toLogPriority(): Int =
    when (this) {
        Level.VERBOSE -> Log.VERBOSE
        Level.DEBUG -> Log.DEBUG
        Level.INFO -> Log.INFO
        Level.WARN -> Log.WARN
        Level.ERROR -> Log.ERROR
        Level.ASSERT -> Log.ASSERT
    }

/**
 * Should be performed in the onCreate() method of Application lifecycle
 */
fun Application.registerLifecycleLoggers(
    processCategory: Category = Category(TYPE_PROCESS),
    activityCategory: Category = Category(TYPE_ACTIVITY),
    fragmentCategory: Category = Category(TYPE_FRAGMENT),
    channel: Int = LogCatChannel.ID,
    separator: String = DEFAULT_SEPARATOR
) {
    ProcessLifecycleOwner.get().lifecycle.addObserver(ProcessLifecycleLogger(processCategory, channel, processIdentifier()))
    this.registerActivityLifecycleCallbacks(ActivityLifecycleLoggingInserter(activityCategory, channel, separator))
    this.registerActivityLifecycleCallbacks(FragmentLifecycleLoggingInserter(fragmentCategory, channel, separator))
}

/**
 * Should be performed in the onCreate() method of Service lifecycle
 */
fun LifecycleService.registerLifecycleLogger(category: Category = Category(TYPE_SERVICE), channel: Int, separator: String = DEFAULT_SEPARATOR) {
    lifecycle.addObserver(ServiceLifecycleLogger(this, category, channel, separator))
}
