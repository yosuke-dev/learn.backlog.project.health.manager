package com.example.domain.value.issue

data class CustomCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: CustomCount) = value.compareTo(other.value)

    init {
        if (value < MIN_VALUE) throw IllegalArgumentException("value must be greater than or equal to $MIN_VALUE.")
    }
}
