package com.example.domain.value.healthmanage

open class HealthStatus(open val value: Float){
    enum class StatusType {
        VERY_HEALTHY,
        HEALTHY,
        NORMAL,
        OBSERVATION_NEEDED,
        HAVE_MEDICAL_EXAMINATION,
    }

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
}
