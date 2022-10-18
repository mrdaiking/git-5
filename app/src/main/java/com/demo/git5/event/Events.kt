package com.demo.git5.event

/**
 * Event list
 */
data class ShowURLEvent(val url: String) : Event {
    override val type: EventType = EventType.SHOW_URL
}
