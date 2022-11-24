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

import android.app.Activity
import android.os.Process
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleService
import sh.vcm.sensiblelogging.Category

internal const val TYPE_ACTIVITY = "Activity"
internal const val TYPE_SERVICE = "Service"
internal const val TYPE_FRAGMENT = "Fragment"
internal const val TYPE_PROCESS = "Process"
internal fun Activity?.identifier(): String = this?.javaClass?.simpleName ?: ""
internal fun LifecycleService.identifier(): String = this.javaClass.simpleName
internal fun processIdentifier() = "Process with PID: ${Process.myPid()}"
internal const val DEFAULT_SEPARATOR = "->"

internal class ActivityLifecycleLogger(activity: FragmentActivity, category: Category, channel: Int, separator: String) :
    LifecycleLogger(
        identifier = activity.identifier(),
        type = TYPE_ACTIVITY,
        separator = separator,
        category = category,
        channel = channel
    )

internal class ServiceLifecycleLogger(service: LifecycleService, category: Category, channel: Int, separator: String) :
    LifecycleLogger(
        identifier = service.identifier(),
        type = TYPE_SERVICE,
        separator = separator,
        category = category,
        channel = channel
    )

internal class ProcessLifecycleLogger(category: Category, channel: Int, separator: String) :
    LifecycleLogger(
        identifier = processIdentifier(),
        type = TYPE_PROCESS,
        separator = separator,
        category = category,
        channel = channel
    )
