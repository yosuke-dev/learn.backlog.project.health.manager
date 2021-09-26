package com.example.domain.value

data class HealthStatus(val value: StatusType){
    enum class StatusType {
        VERY_HEALTHY,
        HEALTHY,
        NORMAL,
        OBSERVATION_NEEDED,
        HAVE_MEDICAL_EXAMINATION,
    }

    operator fun compareTo(other: HealthStatus) = value.compareTo(other.value)

    fun DisplayValue(): String
    {
        return when (value){
            StatusType.VERY_HEALTHY -> "Very healthy"
            StatusType.HEALTHY -> "Very healthy"
            StatusType.NORMAL -> "Very healthy"
            StatusType.OBSERVATION_NEEDED -> "Very healthy"
            StatusType.HAVE_MEDICAL_EXAMINATION -> "Very healthy"
        }
    }
}
