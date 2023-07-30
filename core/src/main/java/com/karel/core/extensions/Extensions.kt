package com.karel.core.extensions

import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout

/**
 * Wait for the transition to complete so that the given transition is fully displayed.
 *
 * @param timeout Timeout for the transition to take place. Defaults to 5 seconds.
 */
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun MotionLayout.awaitTransitionComplete(timeout: Long = 5000L) {

    val TAG = "MotionLayout.awaitTransitionComplete"

    var listener: MotionLayout.TransitionListener? = null

    try {
        withTimeout(timeout) {
            suspendCancellableCoroutine<Unit> { continuation ->

                listener = object : MotionLayoutTransitionListener() {
                    override fun onTransitionCompleted(
                        motionLayout: MotionLayout,
                        currentId: Int
                    ) {
                        super.onTransitionCompleted(motionLayout, currentId)
                        apply(::removeTransitionListener)
                        continuation.resume(Unit, Throwable::printStackTrace)
                    }
                }

                continuation.invokeOnCancellation {
                    listener?.apply(this@awaitTransitionComplete::removeTransitionListener)
                }

                addTransitionListener(listener)
            }
        }
    } catch (ex: TimeoutCancellationException) {
        Log.d(TAG, "TimeoutCancellationException ${ex.message}")
        listener?.apply(this::removeTransitionListener)
    }
}

open class MotionLayoutTransitionListener : MotionLayout.TransitionListener {

    private val TAG = "MotionLayoutTransitionListener"

    override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {
        Log.d(TAG, "onTransitionStarted $startId $endId")
    }

    override fun onTransitionChange(
        motionLayout: MotionLayout,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
        Log.d(TAG, "onTransitionCompleted $currentId")
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout,
        triggerId: Int,
        endId: Boolean,
        progress: Float
    ) {
        Log.d(TAG, "onTransitionStarted $triggerId $endId $progress")
    }
}