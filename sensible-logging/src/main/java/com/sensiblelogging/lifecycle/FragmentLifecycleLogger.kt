package com.sensiblelogging.lifecycle

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sensiblelogging.Log

class FragmentLifecycleLogger(private val category: String, private val separator: String) :
    FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        log(f, "onFragmentViewCreated")
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentStopped")
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        log(f, "onFragmentCreated")
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentResumed")
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        log(f, "onFragmentAttached")
    }

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        log(f, "onFragmentPreAttached")
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentDestroyed")
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        log(f, "onFragmentSaveInstanceState")
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentStarted")
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentViewDestroyed")
    }

    override fun onFragmentPreCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        log(f, "onFragmentPreCreated")
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        log(f, "onFragmentActivityCreated")
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentPaused")
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentDetached")
    }

    private fun log(fragment: Fragment, method: String) {
        // Suppress because it's in fact nullable but uses androidx.annotation.Nullable annotation
        @Suppress("UNNECESSARY_SAFE_CALL")
        Log.d(
            "${fragment.identifier()} $separator $method",
            mapOf(
                TYPE_FRAGMENT to fragment.identifier(),
                TYPE_ACTIVITY to fragment?.activity.identifier().orEmpty(),
                "method" to method
            ),
            category
        )
    }

    private fun Fragment.identifier(): String = this.javaClass.simpleName
    private fun Activity.identifier(): String = this.javaClass.simpleName
}
