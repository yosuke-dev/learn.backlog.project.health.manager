package com.example.domain.value.issue

data class InProgressCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: InProgressCount) = value.compareTo(other.value)
}
