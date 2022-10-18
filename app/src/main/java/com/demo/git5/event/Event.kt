package com.demo.git5.event

import java.io.Serializable

/**
 *
 */
interface Event: Serializable {
    val type: EventType
}
