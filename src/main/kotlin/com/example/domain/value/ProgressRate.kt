package com.example.domain.value

data class ProgressRate(val value:Float){
    operator fun compareTo(other: ProgressRate) = value.compareTo(other.value)
}
