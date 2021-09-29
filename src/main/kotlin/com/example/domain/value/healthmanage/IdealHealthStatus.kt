package com.example.domain.value.healthmanage

data class IdealHealthStatus(override val value: Float): HealthStatus(value){
    operator fun compareTo(other: IdealHealthStatus) = value.compareTo(other.value)
}
