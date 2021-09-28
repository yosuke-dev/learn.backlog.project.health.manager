package com.example.domain.value.healthmanage

data class HealthStatus(val value: Float){
    enum class StatusType {
        VERY_HEALTHY,
        HEALTHY,
        NORMAL,
        OBSERVATION_NEEDED,
        HAVE_MEDICAL_EXAMINATION,
    }

    operator fun compareTo(other: HealthStatus) = value.compareTo(other.value)

    fun getType(): StatusType
    {
        return when {
            value < 80 ->  StatusType.HAVE_MEDICAL_EXAMINATION
            value < 95 ->  StatusType.OBSERVATION_NEEDED
            value < 105 ->  StatusType.NORMAL
            value < 120 ->  StatusType.HEALTHY
            else ->  StatusType.VERY_HEALTHY
        }
    }

    fun getTypeName(): String
    {
        return when (getType()){
            StatusType.VERY_HEALTHY -> "Very healthy"
            StatusType.HEALTHY -> "Very healthy"
            StatusType.NORMAL -> "Very healthy"
            StatusType.OBSERVATION_NEEDED -> "Very healthy"
            StatusType.HAVE_MEDICAL_EXAMINATION -> "Very healthy"
        }
    }
}