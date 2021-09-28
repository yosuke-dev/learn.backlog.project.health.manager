package com.example.domain.value.healthmanage

data class PlannedValue(override val value: Int): ExpectedValue(value){
    operator fun compareTo(other: PlannedValue) = value.compareTo(other.value)
}
