package com.example.domain.value.healthmanage

data class IdealValue(override val value: Int): ExpectedValue(value){
    operator fun compareTo(other: IdealValue) = value.compareTo(other.value)
}
