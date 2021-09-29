package com.example.domain.value.shared

import java.util.*

data class DueDate(val value: Date) {
    operator fun compareTo(value: DueDate): Int {
        return this.value.compareTo(value.value)
    }
}
