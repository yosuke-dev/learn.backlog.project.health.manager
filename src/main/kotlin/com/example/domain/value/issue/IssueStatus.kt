package com.example.domain.value.issue

data class IssueStatus(val value: StatusType){
    enum class StatusType {
        Open,
        InProgress,
        Resolved,
        Closed,
        Custom,
    }

    operator fun compareTo(other: IssueStatus) = value.compareTo(other.value)
}