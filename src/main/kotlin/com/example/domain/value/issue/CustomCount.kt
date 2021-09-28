package com.example.domain.value.issue

data class CustomCount(override val value: Int): IssueCount(value){
    operator fun compareTo(other: CustomCount) = value.compareTo(other.value)
}
