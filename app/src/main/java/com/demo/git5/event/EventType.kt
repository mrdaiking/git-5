package com.demo.git5.event

import java.io.Serializable

/** Event definition */
enum class EventType : Serializable {

    SHOW_URL;

    companion object {
        // serializable
    }
}
