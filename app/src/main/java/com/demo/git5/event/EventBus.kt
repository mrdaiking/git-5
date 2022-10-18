package com.demo.git5.event

import android.util.Log
import android.util.SparseArray

/**
 *
 */
object EventBus {
    private val TAG = "EVENT"
    private val hub = SparseArray<MutableList<EventListener>>()

    /**
     * Notify
     */
    fun notify(event: Event) {
        val list = hub[event.type.ordinal]
        if (list != null) {
            for (listener in list) {
                try { listener.onNotify(event) }
                catch (e: Exception) {
                    Log.i(TAG, "failed to notify: listener=$listener, event=$event", e)
                }
            }
        }
    }

    /**
     * Add listener。
     */
    fun addListener(listener: EventListener) {
        for (type in listener.types) {
            var list = hub[type.ordinal]
            if (list == null) {
                list = mutableListOf<EventListener>()
                hub.append(type.ordinal, list)
            }
            if (!list.contains(listener)) list.add(listener)
        }
    }

    /**
     * Remove listener
     */
    fun removeListener(listener: EventListener) {
        for (type in listener.types) {
            hub[type.ordinal]?.remove(listener)
        }
    }
}
