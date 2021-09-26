package com.example.domain.value

data class MilestoneName(val value:String){
    operator fun compareTo(other: MilestoneName) = value.compareTo(other.value)
}
