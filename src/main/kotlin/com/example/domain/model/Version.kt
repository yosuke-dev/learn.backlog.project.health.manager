package com.example.domain.model

import com.example.domain.value.DueDate
import com.example.domain.value.StartDate
import com.example.domain.value.VersionId
import com.example.domain.value.VersionName

data class Version(
    val versionId: VersionId,
    val versionName: VersionName,
    val startDate: StartDate,
    val dueDate: DueDate
)
