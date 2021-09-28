package com.example.domain.value.issue

open class IssueCount(open val value:Int){
    companion object {
        private const val MIN_VALUE = 0
    }

    init {
        if (value < MIN_VALUE) throw IllegalArgumentException("value must be greater than or equal to $MIN_VALUE.")
    }
}
