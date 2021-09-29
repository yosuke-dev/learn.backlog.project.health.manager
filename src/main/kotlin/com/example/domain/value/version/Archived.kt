package com.example.domain.value.version

data class Archived(val value: Boolean){
    operator fun compareTo(other: Archived) = value.compareTo(other.value)
}
