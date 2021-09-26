package com.example.domain.value

import java.util.*

data class StartDate(val value: Date) {
    operator fun compareTo(value: StartDate): Int {
        return this.value.compareTo(value.value)
    }
}
