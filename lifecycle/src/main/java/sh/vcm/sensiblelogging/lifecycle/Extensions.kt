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

package sh.vcm.sensiblelogging.lifecycle

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.ProcessLifecycleOwner
import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.channel.LogCatChannel

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
    ProcessLifecycleOwner.get().lifecycle.addObserver(
        ProcessLifecycleLogger(
            processCategory,
            channel,
            processIdentifier()
        )
    )
    this.registerActivityLifecycleCallbacks(
        ActivityLifecycleLoggingInserter(
            activityCategory,
            channel,
            separator
        )
    )
    this.registerActivityLifecycleCallbacks(
        FragmentLifecycleLoggingInserter(
            fragmentCategory,
            channel,
            separator
        )
    )
}

/**
 * Should be performed in the onCreate() method of Service lifecycle
 */
fun LifecycleService.registerLifecycleLogger(
    category: Category = Category(TYPE_SERVICE),
    channel: Int,
    separator: String = DEFAULT_SEPARATOR
) {
    lifecycle.addObserver(
        ServiceLifecycleLogger(
            this,
            category,
            channel,
            separator
        )
    )
}

fun Lifecycle.registerLifecycleLogger(
    category: Category = Category(TYPE_LIFECYCLE),
    channel: Int,
    identifier: String,
    separator: String = DEFAULT_SEPARATOR
){
    addObserver(
        LifecycleLogger(
            identifier = identifier,
            type = TYPE_LIFECYCLE,
            separator = separator,
            category = category,
            channel = channel
        )
    )
}
