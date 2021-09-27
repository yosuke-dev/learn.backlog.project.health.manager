package com.example.domain.service

import com.example.domain.model.Issues
import com.example.domain.model.Version
import com.example.domain.value.HealthStatus
import com.example.domain.value.IssueCount
import com.example.domain.value.ProgressRate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.ceil

class HealthMeasure {
    companion object {
        fun measurePlannedCount(version: Version, issues: Issues, today: Date): IssueCount {
            return IssueCount(issues.issues.count { issue -> (issue.dueDate?.value ?: version.dueDate.value) < today })
        }

        fun measureIdealCount(version: Version, issues: Issues, today: Date): IssueCount {
            val versionPeriod = ChronoUnit.DAYS.between(version.startDate.value.toInstant(), version.dueDate.value.toInstant()) + 1
            val currentPeriod = ChronoUnit.DAYS.between(version.startDate.value.toInstant(), today.toInstant())
            val adjustedCurrentPeriod = if (currentPeriod < 0) 0 else if (currentPeriod > versionPeriod) versionPeriod else currentPeriod
            val dayPoint = issues.issues.size.div(versionPeriod.toFloat())
            return IssueCount(ceil(dayPoint.times(adjustedCurrentPeriod)).toInt())
        }

        fun measureProgressRate(denominator: IssueCount, molecular: IssueCount): ProgressRate {
            return ProgressRate(ceil(denominator.value.times(10000F).div(molecular.value)).div(100F))
        }

        fun measureHealthStatus(progressRate: ProgressRate): HealthStatus {
            return when {
                progressRate.value < 80 ->  HealthStatus(HealthStatus.StatusType.HAVE_MEDICAL_EXAMINATION)
                progressRate.value < 95 ->  HealthStatus(HealthStatus.StatusType.OBSERVATION_NEEDED)
                progressRate.value < 105 ->  HealthStatus(HealthStatus.StatusType.NORMAL)
                progressRate.value < 120 ->  HealthStatus(HealthStatus.StatusType.HEALTHY)
                else ->  HealthStatus(HealthStatus.StatusType.VERY_HEALTHY)
            }
        }
    }
}