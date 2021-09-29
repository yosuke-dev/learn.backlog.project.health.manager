package com.example.domain.value.issue

data class ResolvedCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: ResolvedCount) = value.compareTo(other.value)

    init {
        if (value < MIN_VALUE) throw IllegalArgumentException("value must be greater than or equal to $MIN_VALUE.")
    }
}
