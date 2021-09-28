package com.example.domain.value.issue

data class OpenCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: OpenCount) = value.compareTo(other.value)
}
