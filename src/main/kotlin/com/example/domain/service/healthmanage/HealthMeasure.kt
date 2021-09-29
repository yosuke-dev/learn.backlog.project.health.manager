package com.example.domain.service.healthmanage

import com.example.domain.model.issue.Issues
import com.example.domain.model.version.Version
import com.example.domain.value.healthmanage.*
import com.example.domain.value.issue.ClosedCount
import com.example.domain.value.issue.ResolvedCount
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.ceil

class HealthMeasure {
    companion object {
        fun measurePlannedValue(version: Version, issues: Issues, today: Date): PlannedValue {
            return PlannedValue(issues.value.count { issue -> (issue.dueDate?.value ?: version.dueDate.value) < today })
        }

        fun measureIdealValue(version: Version, issues: Issues, today: Date): IdealValue {
            val versionPeriod = ChronoUnit.DAYS.between(version.startDate.value.toInstant(), version.dueDate.value.toInstant()) + 1
            val currentPeriod = ChronoUnit.DAYS.between(version.startDate.value.toInstant(), today.toInstant())
            val adjustedCurrentPeriod = if (currentPeriod < 0) 0 else if (currentPeriod > versionPeriod) versionPeriod else currentPeriod
            val dayPoint = issues.value.size.div(versionPeriod.toFloat())
            return IdealValue(ceil(dayPoint.times(adjustedCurrentPeriod)).toInt())
        }

        fun measurePlannedHealth(resolvedCount: ResolvedCount, closedCount: ClosedCount, plannedValue: PlannedValue): PlannedHealthStatus {
            return PlannedHealthStatus(measureHealth(resolvedCount.value.plus(closedCount.value), plannedValue.value))
        }

        fun measureIdealHealth(resolvedCount: ResolvedCount, closedCount: ClosedCount, idealValue: IdealValue): IdealHealthStatus {
            return IdealHealthStatus(measureHealth(resolvedCount.value.plus(closedCount.value), idealValue.value))
        }

        private fun measureHealth(denominator: Int, molecular: Int): Float {
            return ceil(denominator.times(10000F).div(molecular)).div(100F)
        }
    }
}