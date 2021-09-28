package com.example.domain.value.issue

data class AllCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: AllCount) = value.compareTo(other.value)
}
