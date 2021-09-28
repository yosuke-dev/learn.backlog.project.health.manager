package com.example.domain.value.issue

data class ResolvedCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: ResolvedCount) = value.compareTo(other.value)
}
