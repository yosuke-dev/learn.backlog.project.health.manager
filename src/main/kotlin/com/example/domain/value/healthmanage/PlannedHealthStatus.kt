package com.example.domain.value.healthmanage

data class PlannedHealthStatus(override val value: Float): HealthStatus(value){
    operator fun compareTo(other: PlannedHealthStatus) = value.compareTo(other.value)
}
