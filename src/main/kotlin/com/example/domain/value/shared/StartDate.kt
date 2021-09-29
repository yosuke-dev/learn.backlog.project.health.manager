package com.example.domain.value.shared

import java.util.*

data class StartDate(val value: Date) {
    operator fun compareTo(value: StartDate): Int {
        return this.value.compareTo(value.value)
    }
}
