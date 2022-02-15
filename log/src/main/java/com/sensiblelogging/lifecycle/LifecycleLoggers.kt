package com.sensiblelogging.lifecycle

import android.app.Activity
import android.os.Process
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleService

internal const val TYPE_ACTIVITY = "Activity"
internal const val TYPE_SERVICE = "Service"
internal const val TYPE_FRAGMENT = "Fragment"
internal const val TYPE_PROCESS = "Process"
internal fun Activity?.identifier(): String = this?.javaClass?.simpleName ?: ""
internal fun LifecycleService.identifier(): String = this.javaClass.simpleName
internal fun processIdentifier() = "Process with PID: ${Process.myPid()}"
internal const val DEFAULT_SEPARATOR = "->"

internal class ActivityLifecycleLogger(activity: FragmentActivity, category: String, separator: String) :
    LifecycleLogger(
        identifier = activity.identifier(),
        type = TYPE_ACTIVITY,
        separator = separator,
        category = category
    )

internal class ServiceLifecycleLogger(service: LifecycleService, category: String, separator: String) :
    LifecycleLogger(
        identifier = service.identifier(),
        type = TYPE_SERVICE,
        separator = separator,
        category = category
    )

internal class ProcessLifecycleLogger(category: String, separator: String) :
    LifecycleLogger(
        identifier = processIdentifier(),
        type = TYPE_PROCESS,
        separator = separator,
        category = category
    )
