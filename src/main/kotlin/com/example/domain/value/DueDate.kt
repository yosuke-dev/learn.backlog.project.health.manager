package com.example.domain.value

import java.util.*

data class DueDate(val value: Date) {
    operator fun compareTo(value: DueDate): Int {
        return this.value.compareTo(value.value)
    }
}
