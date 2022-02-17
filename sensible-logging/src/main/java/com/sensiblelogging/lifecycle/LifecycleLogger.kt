package com.sensiblelogging.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.sensiblelogging.Log

abstract class LifecycleLogger(
    private val identifier: String,
    private val type: String,
    private val separator: String,
    private val category: String
) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        log("onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        log("onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        log("onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        log("onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        log("onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        log("onDestroy")
    }

    private fun log(method: String) {
        Log.d(
            "$identifier $separator $method",
            mapOf(
                type to identifier,
                "method" to method
            ),
            category
        )
    }
}
