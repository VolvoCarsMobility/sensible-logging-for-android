package co.m.common.utils.logging.lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

internal class ActivityLifecycleLoggingInserter(private val category: String, private val separator: String) :
    ActivityOnCreateListener(doOnCreate = {
        it.lifecycle.addObserver(
            ActivityLifecycleLogger(it, category, separator)
        )
    })

internal class FragmentLifecycleLoggingInserter(private val category: String, private val separator: String) :
    ActivityOnCreateListener(doOnCreate = {
        it.supportFragmentManager.registerFragmentLifecycleCallbacks(
            FragmentLifecycleLogger(category, separator),
            true
        )
    })

internal abstract class ActivityOnCreateListener(private val doOnCreate: (FragmentActivity) -> Unit) :
    SimpleActivityLifeCycleCallbacks() {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        (activity as? FragmentActivity)?.apply {
            doOnCreate.invoke(this)
        }
    }
}
