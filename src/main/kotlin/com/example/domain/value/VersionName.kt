package com.example.domain.value

data class VersionName(val value:String){
    operator fun compareTo(other: VersionName) = value.compareTo(other.value)
}
