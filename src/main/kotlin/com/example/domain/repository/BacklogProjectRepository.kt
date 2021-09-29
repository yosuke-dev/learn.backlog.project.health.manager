package com.example.domain.repository

import com.example.domain.model.issue.Issues
import com.example.domain.model.version.Versions
import com.example.domain.value.project.ProjectId
import com.example.domain.value.version.VersionId

interface BacklogProjectRepository {
    fun getVersionsByProjectId(projectId: ProjectId): Versions

    fun getIssuesByVersions(projectId: ProjectId, versionIds: List<VersionId>): Issues
}