package com.example.domain.value

data class ProjectId(val value:Long){
    companion object {
        private const val MIN_VALUE = 0
    }

    init {
        if (value < MIN_VALUE) throw IllegalArgumentException("value must be greater than or equal to $MIN_VALUE.")
    }

    operator fun compareTo(other: ProjectId) = value.compareTo(other.value)
}
