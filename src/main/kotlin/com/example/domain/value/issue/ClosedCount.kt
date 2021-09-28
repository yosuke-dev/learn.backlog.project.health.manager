package com.example.domain.value.issue

data class ClosedCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: ClosedCount) = value.compareTo(other.value)
}
