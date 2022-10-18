package com.demo.git5.event

/**
 *
 */
interface EventListener {

    /**  */
    val types: List<EventType>

    /**  */
    fun onNotify(event: Event)
}
