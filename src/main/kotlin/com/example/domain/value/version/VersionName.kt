package com.example.domain.value.version

data class VersionName(val value:String){
    operator fun compareTo(other: VersionName) = value.compareTo(other.value)
}
