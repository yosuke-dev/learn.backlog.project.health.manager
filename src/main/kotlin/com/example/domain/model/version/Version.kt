package com.example.domain.model.version

import com.example.domain.value.DueDate
import com.example.domain.value.StartDate
import com.example.domain.value.version.VersionId
import com.example.domain.value.version.VersionName

data class Version(
    val versionId: VersionId,
    val versionName: VersionName,
    val startDate: StartDate,
    val dueDate: DueDate
)
